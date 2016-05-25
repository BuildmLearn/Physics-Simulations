package org.buildmlearn.physicssimulations.utils;

import android.graphics.Color;

import org.buildmlearn.physicssimulations.Atwood;
import org.buildmlearn.physicssimulations.CircuitBuilder;
import org.buildmlearn.physicssimulations.Collision;
import org.buildmlearn.physicssimulations.Lens;
import org.buildmlearn.physicssimulations.LightInterference;
import org.buildmlearn.physicssimulations.LightRefraction;
import org.buildmlearn.physicssimulations.Pendulum;
import org.buildmlearn.physicssimulations.Projectile;
import org.buildmlearn.physicssimulations.R;
import org.buildmlearn.physicssimulations.Simulation;
import org.buildmlearn.physicssimulations.SimulationType;
import org.buildmlearn.physicssimulations.Spring;
import org.buildmlearn.physicssimulations.Wave;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final List<Simulation> SIMULATION_LIST = new ArrayList<Simulation>(){
        {
            add(new Simulation("Atwood",
                    "Simulation Details"));
            add(new Simulation("Circuit Builder",
                    "Simulation Details"));
            add(new Simulation("Collision",
                    "Simulation Details"));
            add(new Simulation("Lens",
                    "Simulation Details"));
            add(new Simulation("Light Interference",
                    "Simulation Details"));
            add(new Simulation("Light Refraction",
                    "Simulation Details"));
            add(new Simulation("Pendulum",
                    "Simulation Details"));
            add(new Simulation("Projectile",
                    "Simulation Details"));
            add(new Simulation("Spring","" +
                    "Simulation Details"));
            add(new Simulation("Wave",
                    "Simulation Details"));
        }
    };

    public enum TYPE {
        SIM, TEST, DETAILS
    }

    public static final int COLOR_MASK = R.color.colorAccent; //Color.parseColor("#ddFF4081");

    public static final String SIM_NAME = "sim_name";
    public static final String SIM_DETAILS = "sim_details";

    public static SimulationType getSim(String name) {
        if (name.equals("Atwood"))
            return new Atwood();
        if (name.equals("Circuit Builder"))
            return new CircuitBuilder();
        if (name.equals("Collision"))
            return new Collision();
        if (name.equals("Lens"))
            return new Lens();
        if (name.equals("Light Interference"))
            return new LightInterference();
        if (name.equals("Light Refraction"))
            return new LightRefraction();
        if (name.equals("Pendulum"))
            return new Pendulum();
        if (name.equals("Projectile"))
            return new Projectile();
        if (name.equals("Spring"))
            return new Spring();
        if (name.equals("Wave"))
            return new Wave();
        return null;
    }

    public static int getId(String name) {
        if (name.equals("Atwood"))
            return 0;
        if (name.equals("Circuit Builder"))
            return 1;
        if (name.equals("Collision"))
            return 2;
        if (name.equals("Lens"))
            return 3;
        if (name.equals("Light Interference"))
            return 4;
        if (name.equals("Light Refraction"))
            return 5;
        if (name.equals("Pendulum"))
            return 6;
        if (name.equals("Projectile"))
            return 7;
        if (name.equals("Spring"))
            return 8;
        if (name.equals("Wave"))
            return 9;
        return 0;
    }

    public static int getIcon(String name) {
        if (name.equals("Atwood"))
            return R.drawable.pulley;
        if (name.equals("Circuit Builder"))
            return R.drawable.battery;
        if (name.equals("Collision"))
            return R.drawable.collision;
        if (name.equals("Lens"))
            return R.drawable.lens;
        if (name.equals("Light Interference"))
            return R.drawable.bulb;
        if (name.equals("Light Refraction"))
            return R.drawable.flashlight;
        if (name.equals("Pendulum"))
            return R.drawable.pendulum;
        if (name.equals("Projectile"))
            return R.drawable.projectile;
        if (name.equals("Spring"))
            return R.drawable.spring;
        if (name.equals("Wave"))
            return R.drawable.wave;
        return 0;
    }

    public static final int PROBLEM = 0;
    public static final int ANSWER_1 = 1;
    public static final int ANSWER_2 = 2;
    public static final int ANSWER_3 = 3;
    public static final int ANSWER_4 = 4;
    public static final int ANSWER_CORRECT = 5;

    public static final String[][][] PROBLEMS = {
            {
                    {
                            "A rope is used to pull a 2.89-kg bucket of water out of a deep well. What is the acceleration of the bucket when the tension in the rope is 30.2 N?",
                            "0.64 m/s/s, up",
                            "0.65 m/s/s, up",
                            "0.62 m/s/s, up",
                            "0.63 m/s/s, up",
                            "0.65 m/s/s, up"
                    },
                    {
                            "A 200.0-gram mass (m1) and 50.0-gram mass (m2) are connected by a string. The string is stretched over a pulley. Determine the acceleration of the masses and the tension in the string.",
                            "5.88 m/s/s and 0.784 N",
                            "5.92 m/s/s and 0.783 N",
                            "5.95 m/s/s and 0.780 N",
                            "5.98 m/s/s and 0.779 N",
                            "5.88 m/s/s and 0.784 N"
                    },
                    {
                            "A 200.0-gram mass (m1) and 50.0-gram mass (m2) are connected by a string. The string is stretched over a pulley. Determine the acceleration of the masses and the tension in the string.",
                            "5.88 m/s/s and 0.784 N",
                            "5.92 m/s/s and 0.783 N",
                            "5.95 m/s/s and 0.780 N",
                            "5.98 m/s/s and 0.779 N",
                            "5.88 m/s/s and 0.784 N"
                    }
            },
            //circuit
            {
                    {
                            "A coffee cup immersion heater utilizes a heating coil with a resistance of 8.5 Ω. Determine the current through the coil when operated at 110.5 V.",
                            "12.7 A",
                            "13 A",
                            "11.5 A",
                            "12 A",
                            "13 A"
                    },
                    {
                            "The power of a 1.5-volt alkaline cell varies with the number of hours of operation. A brand new D-cell can deliver as much as 13 A through a copper wire connected between terminals. Determine the power of a brand new D-cell.",
                            "20.5 W",
                            "18.5 W",
                            "19.5 W",
                            "19 W",
                            "19.5 W"
                    },
                    {
                            "The UL panel on the bottom of an electric toaster oven indicates that it operates at 1500 W on a 110 V circuit. Determine the electrical resistance of the toaster oven.",
                            "8.1 Ω",
                            "7.2 Ω",
                            "7.3 Ω",
                            "8.4 Ω",
                            "8.1 Ω"
                    }
            },
            //collision
            {
                    {
                            "Anna Litical and Noah Formula are doing The Cart and the Brick Lab. They drop a brick on a 2.6 kg cart moving at 28.2 cm/s. After the collision, the dropped brick and cart are moving together with a velocity of 15.7 cm/s. Determine the mass of the dropped brick.",
                            "2.3 kg",
                            "2.6 kg",
                            "2.1 kg",
                            "2.9 kg",
                            "2.1 kg"
                    },
                    {
                            "Rex (m=86 kg) and Tex (92 kg) board the bumper cars at the local carnival. Rex is moving at a full speed of 2.05 m/s when he rear-ends Tex who is at rest in his path. Tex and his 125-kg car lunge forward at 1.40 m/s. Determine the post-collision speed of Rex and his 125-kg car.",
                            "0.63 m/s",
                            "0.61 m/s",
                            "0.62 m/s",
                            "0.59 m/s",
                            "0.61 m/s"
                    },
                    {
                            "Bailey is on the tenth frame of her recent bowling competition and she needs to pick up the last pin for a spare and the first place trophy. She rolls the 7.05-kg ball down the lane and it hits the 1.52-kg pin head on. The ball was moving at 8.24 m/s before the collision. The pin went flying forward at 13.2 m/s. Determine the post-collision speed of the ball.",
                            "5.39 m/s",
                            "5.42 m/s",
                            "5.51 m/s",
                            "5.38 m/s",
                            "5.39 m/s"
                    }
            },
            //lens
            {
                    {
                            "During a lens lab, Jerome and Michael placed a 4.5-cm tall night light bulb a distance of 42.8 cm from a lens. The image of the light bulb was inverted and appeared 26.5 cm from the lens. Determine the focal length of the lens being by Jerome and Michael.",
                            "15.9 cm",
                            "16.2 cm",
                            "16.6 cm",
                            "16.4 cm",
                            "16.4 cm"
                    },
                    {
                            "The converging lens on Julia's camera has a focal length of 52 mm. She uses the camera to take a picture of her friends at South's homecoming. Her friends are located a distance of 2.45 m from the camera as Julia focuses in on them. Calculate the distance from the lens to the film (i.e, the image distance).",
                            "52.9 mm",
                            "53.4 mm",
                            "53.1 mm",
                            "53.2 mm",
                            "53.1 mm"
                    },
                    {
                            "A lens produces a virtual image located 33.8 cm from the lens when the object is located 18.5 cm from the lens. Determine the focal length of the lens. What kind of lens is it?",
                            "40.9 cm\n" +
                                    "Lens type: converging",
                            "40.9 cm\n" +
                                    "Lens type: diverging",
                            "38.7 cm\n" +
                                    "Lens type: converging",
                            "38.7 cm\n" +
                                    "Lens type: diverging",
                            "40.9 cm\n" +
                                    "Lens type: converging"
                    }
            },
            //interference
            {
                    {"Monochromatic yellow light (λ = 594 nm) passes throught " +
                            "two slits with a slit spacing of 0.125 mm and forms an interference pattern on a screen that" +
                            "is positioned 14.5 m away. Determine the distance between the fifth bright spots on opposite " +
                            "sides of the central bright spot.\n",
                            "68.9 cm",
                            "71.5 cm",
                            "67.8 cm",
                            "72.9 cm",
                            "68.9 cm"
                    },
                    {
                            "Water waves with a wavelength of 7.8 cm are created in a ripple tank by two in-phase sources bobbing up and down at the same frequency. The waves form an interference pattern in the space surrounding the sources. A point on the fourth nodal line is a distance of 58.2 cm from the nearest source. Determine the distance from this same point to the furthest source.",
                            "85.5 cm",
                            "86.5 cm",
                            "86.2 cm",
                            "85.9 cm",
                            "85.5 cm"
                    },
                    {
                            "Mr. H’s period 7 physics class is attempting to duplicate Thomas Young’s experiment in which they use a two-point source light interference pattern to measure the wavelength of light. They red shine laser light through a slide containing a double slit; the slit spacing is 0.125 mm. The light interference pattern created by the light which passes through the slits is projected on a screen a distance of 10.72 m away. Justin and Shirley measure the distance from the 3rd antinodal bright spots on opposite sides of the pattern to be 33.9 cm apart. Based on these measurements, what is the wavelength of the red laser light.",
                            "659 nm",
                            "658 nm",
                            "661 nm",
                            "660 nm",
                            "659 nm"
                    }
            },
            //refraction
            {
                    {
                            "A ray of light travels through air (n = 1.00) and approaching the boundary with water (n = 1.33). The angle of incidence is 45.0°. Determine the angle of refraction",
                            "33.5°",
                            "31.1°",
                            "32.1°",
                            "31.7°",
                            "32.1°"
                    },
                    {
                            "A light ray is passing through water (n=1.33) towards the boundary with a transparent solid at an angle of 56.4°. The light refracts into the solid at an angle of refraction of 42.1°. Determine the index of refraction of the unknown solid.",
                            "1.33",
                            "1.65",
                            "1.54",
                            "1.25",
                            "1.65"
                    },
                    {
                            "Light in air approaches the boundary of oil at an angle of 36.1 degress with respect to the normal. The light travels at a speed of 2.27 x 108 m/s through the oil. Determine the angle of refraction.",
                            "24.5°",
                            "25.6°",
                            "26.3°",
                            "26.5°",
                            "26.5°"
                    }
            },
            //pendulum
            {
                    {
                            "A pendulum clock was designed to have a period of 1.50 seconds, but due to low quality of its manufacturing its length stretches 0.012 meters beyond the designed value. How long is the actual period? ",
                            "0.5600 m",
                            "0.5615 m",
                            "0.5585 m",
                            "0.5595 m",
                            "0.5585 m"
                    },
                    {
                            "A wrecking ball is hanging from a 16m long steel cable and almost touching a wall to be demolished. It is pulled a short distance from the vertical line and let go from rest. Calculate the time it will take to reach the wall.",
                            "2.2 s",
                            "2.0 s",
                            "2.5 s",
                            "3.0 s",
                            "2.0 s"
                    },
                    {
                            "A pendulum swings at one Hertz. How long is the pendulum in centimeters?",
                            "25.0 cm",
                            "24.8 cm",
                            "24.7 cm",
                            "25.2 cm",
                            "24.8 cm"
                    }
            },
            //projectile
            {
                    {
                            "An emergency relief plane is dropping a care package from a plane to a group of medical personnel working for a relief agency in an African village. The package is designed to land in a small lake, inflate an attached raft upon impact, and finally resurface with the raft side down. The plane will be moving horizontally with a ground speed of 59.1 m/s. The package will be dropped a horizontal distance of 521 m from the intended target location. At what altitude above the pond must the plane be flying in order to successfully accomplish this feat?",
                            "392 m",
                            "368 m",
                            "375 m",
                            "381 m",
                            "381 m"
                    },
                    {
                            "A tennis player stretches out to reach a ball that is just barely above the ground and successfully 'lobs' it over her opponent's head. The ball is hit with a speed of 18.7 m/s at an angle of 65.1 degrees. Determine the maximum height which the ball reaches.",
                            "14.7 m",
                            "14.8 m",
                            "14.3 m",
                            "15.2 m",
                            "14.7 m"
                    },
                    {
                            "Albert is South’s star punter for the varsity football team. His best hang time this past season was for a punt which he kicked at 74° above the horizontal. The punt had a 6.2 second hang time. Determine the speed at which the ball was punted.",
                            "31.9 m/s",
                            "32.3 m/s",
                            "31.6 m/s",
                            "32.1 m/s",
                            "31.6 m/s"
                    }
            },
            //spring
            {
                    {
                            "A force of 16 N is required to stretch a spring a distance of 40 cm from its rest position. What force (in Newtons) is required to stretch the same spring twice the distance?",
                            "8 N",
                            "34 N",
                            "48 N",
                            "32 N",
                            "32 N"
                    },
                    {
                            "A spring is of length 22 cm/s.If it is loaded with with 2 kg it gets stretched by 38 cm/s. Calculate its spring constant.",
                            "-2 N/m",
                            "-1.5 N/m",
                            "-1 N/m",
                            "-1.75 N/m",
                            "-2 N/m"
                    },
                    {
                            "Calculate how much potential energy a spring gains as its compression is changed from a compression of 0.10m to a compression of 0.50 metres.  The spring constant is 200 N/m.",
                            "12 J",
                            "18 J",
                            "24 J",
                            "26 J",
                            "24 J"
                    }
            },
            //wave
            {
                    {
                            "Jerome and Claire are doing the Period of a Pendulum Lab. They observe that a pendulum makes exactly 10 complete back and forth cycles of motion in 21.8 seconds. Determine the period of the pendulum.",
                            "2.04 s",
                            "1.97 s",
                            "2.18 s",
                            "2.21 s",
                            "2.18 s"
                    },
                    {
                            "The spin rate of a CD-ROM varies according to the location on the disc from where data is being accessed. When accessing data from the inner circles of the disc, the CD can spin at a rate as high as 400 revolutions per minute. Determine the frequency (in Hertz) and the period (in seconds) of the spinning CD.",
                            "Frequency = 6.67 Hz\n" +
                                    "Period = 0.150 s",
                            "Frequency = 5.48 Hz\n" +
                                    "Period = 0.160 s",
                            "Frequency = 6.35 Hz\n" +
                                    "Period = 0.150 s",
                            "Frequency = 6.67 Hz\n" +
                                    "Period = 0.140 s",
                            "Frequency = 6.67 Hz\n" +
                                    "Period = 0.150 s"
                    },
                    {
                            "Microbats use echolocation to navigate and hunt. They emit pulses of high frequency sound waves which reflect off obstacles and objects in their surroundings. By detecting the time delay between the emitted pulse and the return of the reflected pulse, a bat can determine the location of the object. Determine the time delay between the sending of a pulse and the return of its reflection from an object located 12.5 m away. Approximate the speed of the sound waves as 345 m/s.",
                            "0.0700 seconds",
                            "0.0675 seconds",
                            "0.0750 seconds",
                            "0.0725 seconds",
                            "0.0725 seconds"
                    }
            }
    };

}
