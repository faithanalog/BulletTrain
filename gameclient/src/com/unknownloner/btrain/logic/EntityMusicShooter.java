package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.osuparse.BeatmapObject;
import com.unknownloner.btrain.osuparse.BeatmapSlider;
import com.unknownloner.btrain.osuparse.BeatmapSpinner;
import org.lwjgl.opengl.Display;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityMusicShooter extends Entity {

    public Clip clip;

    private List<BeatmapObject> objs = new ArrayList<>();
    private int curObj = 0;

    public boolean firstTick = true;

    public int bulletQueue;

    public int bulletQueueCooldown = 0;


    EntityMusicShooter(Level level, String map) {
        super(level);
        try {
            AudioInputStream is = AudioSystem.getAudioInputStream(EntityMusicShooter.class.getResourceAsStream(map + "/song.wav"));
            clip = AudioSystem.getClip();
            clip.open(is);

            String mapText = Util.readText(map + "/map.osu");
            String[] lines = mapText.split("\n");
            for (String ln : lines) {
                BeatmapObject obj = BeatmapObject.parse(ln);
                if (obj != null) {
                    objs.add(obj);
                }
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void startPlaying() {
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    @Override
    public void tick() {
        if (firstTick) {
            firstTick = false;
            startPlaying();
            return;
        }

        if (curObj >= objs.size())
            return;

        long now = clip.getMicrosecondPosition();
        BeatmapObject obj = objs.get(curObj);
        if (now >= obj.time) {
            curObj++;
            if (obj instanceof BeatmapSlider) {
                bulletQueue += ((BeatmapSlider)obj).repeats;
            }
            int rand = (int) (1 + Math.random() * 3);
            for(int i = 0; i < rand; i++){
                fireBullet();
            }
        }

        if (bulletQueueCooldown > 0) {
            bulletQueueCooldown--;
        }
        if (bulletQueue > 0 && bulletQueueCooldown == 0) {
            bulletQueue--;
            bulletQueueCooldown = 3;
            int rand = (int) (1 + Math.random() * 3);
            for(int i = 0; i < rand; i++){
                fireBullet();
            }
        }
    }

    public void fireBullet() {
        EntityPlayer player = level.player;

        int ox = -Display.getWidth() / 2;
        int oy = 0;

        oy += (Math.random() - 0.5) * 500;

        EntityBullet bullet = new EntityBullet(level, player.pos.x + ox, player.pos.y + oy, 8.5, 0.0);
        level.spawnEntity(bullet);
    }

    @Override
    public EntityType type() {
        return null;
    }

}
