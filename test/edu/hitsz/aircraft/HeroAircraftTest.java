package edu.hitsz.aircraft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.prop.BulletProp;
import edu.hitsz.prop.BulletPlusProp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;

    @BeforeEach
    void setUp(){
        heroAircraft = HeroAircraft.getHeroAircraft();
        // reset state to a known baseline before each test
        heroAircraft.recoverTrajectory();
        heroAircraft.hp = heroAircraft.maxHp = 100;
        heroAircraft.shootNum = 1;
        heroAircraft.power = 50;
    }

    @Test
    void singleton_getHeroAircraft_returnsSameInstance(){
        HeroAircraft second = HeroAircraft.getHeroAircraft();
        assertNotNull(heroAircraft);
        assertSame(heroAircraft, second);
    }

    @Test
    void shoot_changes_with_trajectory_flags(){
        // default trajectory -> straight shoot (1 bullet)
        List<BaseBullet> straight = heroAircraft.shoot();
        assertEquals(1, straight.size());

        // BulletProp -> scatter (3 bullets)
        heroAircraft.changeTrajectory(new BulletProp(0,0,0,0));
        List<BaseBullet> scatter = heroAircraft.shoot();
        assertEquals(3, scatter.size());

        // BulletPlusProp -> circle (20 bullets)
        heroAircraft.changeTrajectory(new BulletPlusProp(0,0,0,0));
        List<BaseBullet> circle = heroAircraft.shoot();
        assertEquals(20, circle.size());

        heroAircraft.recoverTrajectory();
    }

    @Test
    void addHp_heals_and_caps_at_max(){
        heroAircraft.decreaseHp(60); // hp -> 40
        assertEquals(40, heroAircraft.getHp());
        heroAircraft.addHp(30);
        assertEquals(70, heroAircraft.getHp());
        heroAircraft.addHp(50); // should cap at max (100)
        assertEquals(100, heroAircraft.getHp());
    }
}