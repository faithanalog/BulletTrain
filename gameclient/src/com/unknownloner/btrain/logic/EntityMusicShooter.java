package com.unknownloner.btrain.logic;

import com.unknownloner.btrain.Util;
import com.unknownloner.btrain.osuparse.BeatmapObject;
import com.unknownloner.btrain.osuparse.BeatmapSlider;
import org.lwjgl.opengl.Display;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityMusicShooter extends Entity {

    private Clip clip;

    private List<BeatmapObject> objs = new ArrayList<>();
    private int curObj = 0;

    public boolean firstTick = true;


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
            fireBullet();
            System.out.println("Fire!");
        }
    }

    public void fireBullet() {
        EntityPlayer player = level.player;

        int ox = -Display.getWidth() / 2;
        int oy = Display.getHeight() / 2 - 100;

        EntityBullet bullet = new EntityBullet(level, player.pos.x + ox, player.pos.y + oy, 12.0, 0.0);
        level.spawnEntity(bullet);
    }

    @Override
    public EntityType type() {
        return null;
    }

}
