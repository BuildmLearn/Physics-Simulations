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
            add(new Simulation("Projectile",
                    "Simulation Details"));
            add(new Simulation("Collision",
                    "Simulation Details"));
            add(new Simulation("Spring","" +
                    "Simulation Details"));
            add(new Simulation("Circuit Builder",
                    "Simulation Details"));
            add(new Simulation("Atwood",
                    "Simulation Details"));
            add(new Simulation("Light Interference",
                    "Simulation Details"));
            add(new Simulation("Pendulum",
                    "Simulation Details"));
            add(new Simulation("Light Refraction",
                    "Simulation Details"));
            add(new Simulation("Wave",
                    "Simulation Details"));
            add(new Simulation("Lens",
                    "Simulation Details"));
        }
    };

    public enum TYPE {
        SIM, TEST, DETAILS
    }

    public static final int COLOR_MASK = R.color.colorAccent; //Color.parseColor("#ddFF4081");

    public static final String SIM_NAME = "sim_name";
    public static final String SIM_DETAILS = "sim_details";

    public static final String TUT_LIST     = "tut_list";
    public static final String TUT_TEST     = "tut_test";
    public static final String TUT_DETAILS  = "tut_details";

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

    public static final String[] DETAILS = {
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "<p><b>Refraction</b> is the change in direction of propagation of a wave due to a change in its transmission medium.</p><p>The phenomenon is explained by the conservation of energy and the conservation of momentum. Due to the change of medium, the phase velocity of the wave is changed but its frequency remains constant. This is most commonly observed when a wave passes from one medium to another at any angle other than 0° from the normal. Refraction of light is the most commonly observed phenomenon, but any type of wave can refract when it interacts with a medium, for example when sound waves pass from one medium into another or when water waves move into water of a different depth. Refraction is described by Snell's law, which states that, for a given pair of media and a wave with a single frequency, the ratio of the sines of the angle of incidence <i>θ<sub>1</sub></i> and angle of refraction <i>θ<sub>2</sub></i> is equivalent to the ratio of phase velocities (<i>v</i><sub>1</sub> / <i>v</i><sub>2</sub>) in the two media, or equivalently, to the opposite ratio of the indices of refraction (<i>n</i><sub>2</sub> / <i>n</i><sub>1</sub>):</p><p><strong>sinθ<sub>1</sub>/sinθ<sub>2</sub>=n<sub>2</sub> / n<sub>1</sub></strong></p>",
            "<p>A pendulum is a weight suspended from a pivot so that it can swing freely. When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.</p><h2>Factors and parameters</h2><p>The major factor involved in the equations for calculating the frequency of a pendulum is the length of the rod or wire, provided the initial angle or amplitude of the swing is small. The mass or weight of the bob is <b>not</b> a factor in the frequency of the simple pendulum, but the acceleration of gravity is in the equation.</p><p>Knowing the length of the pendulum, you can determine its frequency. Or, if you want a specific frequency, you can determine the necessary length.</p><h2>Equations for period and frequency</h2><p>The period of the motion for a pendulum is how long it takes to swing back-and-forth, measured in seconds. Period is designated as <b>T</b>.</p><p>The frequency of a pendulum is how many back-and-forth swings there are in a second, measured in hertz. Frequency is usually designated as <b>f</b>.</p><p>The period <b>T</b> is the reciprocal of the frequency. <b>T = 1/f</b> and <b>f = 1/T</b>.</p><h3>Period equation</h3><p>The equation for the period of a simple pendulum starting at a small angle (<b>a</b>) is:</p><p><b>T = 2&#960;&radic;(L/g)</b></p><p>where:</p><b>T</b> is the period in seconds (s)<br><b>&#960;</b> = 3.14<br><b>L</b> is the length of the rod or wire in meters or feet<br><b>g</b> is the acceleration due to gravity (9.8 m/s&sup2; or 32 ft/s&sup2;)<h3>Frequency equation</h3><p>The equation to calculate the frequency of a simple pendulum, starting at a small angle is:</p><p><b>f =[ &radic;(g/L)]/2&pi;</b></p><p>where:</p><b>f</b> = frequency in cycles per second (Hertz or Hz)<br><b>g</b> is the acceleration due to gravity (9.8 m/s&sup2; or 32 ft/s&sup2;)<br><b>L</b> is the length of the rod or wire in meters or feet<h2>Length of wire</h2><p>If you wanted to find the length of the rod or wire for a given frequency, take the equation <b>f = &radic;(g/L)/2&pi;</b> and solve for <b>L</b>. The result is:</p><p><b>L = g/(4&#960;<sup>2</sup>f<sup>2</sup>)</b></p><p>where:</p><b>&#960;<sup>2</sup></b> is pi-squared or <b>pi</b> times <b>pi</b><br><b>f<sup>2</sup></b> is frequency-squared or <b>f</b> times <b>f</b><br><b>4&#960;<sup>2</sup>f<sup>2</sup></b> is 4 times <b>&#960;<sup>2</sup></b> times <b>f<sup>2</sup></b><h2>Velocity</h2><p>Although the velocity of the bob at the bottom of the swing is not a factor in determining frequency, it may be of interest in other calculations.</p><p>The velocity can be approximated from the gravity equation for a weight dropping from a height. The height is determined by the angle from the vertical that is the starting point of the pendulum's swing. Thus, the velocity at the bottom of the swing is:</p><p><b>v = &radic;{2gL[1-cos(θ)]} </b></p><p>where:</p><b>v</b> is the velocity of the weight at the bottom of the swing<br><b>g</b> is the acceleration due to gravity<br><b>L</b> is the length of the wire<br><b>θ</b> is the angle from the vertical<br><b>cos(θ)</b> is the cosine of angle <b>θ</b><p><b>Note</b> that the velocity, as well as the period and frequency are affected by the acceleration due to gravity constant. In other words, a pendulum will swing slower on the Moon than on the Earth, because the gravity on the Moon is less than on the Earth.</p><h3>Energy in a Pendulum</h3><p>In a simple pendulum with no friction, mechanical energy is conserved.  Total mechanical energy is a combination of kinetic energy and gravitational potential energy.   As the pendulum swings back and forth, there is a constant exchange between kinetic energy and gravitational potential energy.</p><h1>Potential Energy</h1><p>The potential energy of the pendulum can be modeled off of the basic equation <strong></strong></p><p><strong>PE = mgh</strong></p><p>where <strong>g</strong> is the acceleration due to gravity and <strong>h</strong> is the height.  We often use this equation to model objects in free fall.</p><p>However, the pendulum is constrained by the rod or string and is not in free fall.  Thus we must express the height in terms of <strong>θ</strong>, the angle and <strong>L,</strong> the length of the pendulum.   Thus <strong>h = L(1 – cos(θ))</strong></p><p>At all points in-between the potential energy can be described using</p><p><strong>PE = mgL(1 – cos(θ))</strong></p><h1>Kinetic Energy</h1><p>Ignoring friction and other non-conservative forces, we find that in a simple pendulum, mechanical energy is conserved.   The kinetic energy would be <strong>KE=  ½mv<sup>2</sup></strong>,where <strong>m</strong> is the mass of the pendulum, and <strong>v </strong>is the speed of the pendulum.</p><p>At its highest point the pendulum is momentarily motionless.  All of the energy in the pendulum is gravitational potential energy and there is no kinetic energy. At the lowest point the pendulum has its greatest speed. All of the energy in the pendulum is kinetic energy and there is no gravitational potential energy.  However, the total energy is constant as a function of time.<p>If there is friction, we have a damped pendulum which exhibits damped harmonic motion. All of the mechanical energy eventually becomes other forms of energy such as heat or sound.</p><h1>Mass and the Period</h1><p>Your investigations should have found that mass does not affect the period  of a pendulum.  One reason to explain this is using conservation of energy.</p><p>If we examine the equations for conservation of energy in a pendulum system we find that mass cancels out of the equations.</p><p>KE<sub>i</sub> + PE<sub>i</sub>= KE<sub>f</sub>+PE<sub>f</sub></p><p>[½mv<sup>2</sup> + mgL(1-cos(θ)) ]<sub>i</sub> =  [½mv<sup>2</sup> + mgL(1-cos(θ)) ]<sub>f</sub></p><p>There is a direct relationship between the angle <strong>θ</strong> and the velocity.  Because of this, the mass does not affect the  behavior of the pendulum and does not alter the period of the pendulum.</p>",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "A pendulum is a weight suspended from a pivot so that it can swing freely.[1] When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.",
            "<p>In physics, a <strong>wave</strong> is an oscillation accompanied by a transfer of energy that travels through medium (space or mass). Frequency refers to the addition of time. Wave motion transfers energy from one point to another, which displace particles of the transmission medium — that is, with little or no associated mass transport. Waves consist, instead, of oscillations or vibrations (of a physical quantity), around almost fixed locations.</p><p>There are two main types of waves. Mechanical waves propagate through a medium, and the substance of this medium is deformed. The deformation reverses itself owing to restoring forces resulting from its deformation. For example, sound waves propagate via air molecules colliding with their neighbors. When air molecules collide, they also bounce away from each other (a restoring force). This keeps the molecules from continuing to travel in the direction of the wave.The second main type of wave, electromagnetic waves, do not require a medium. Instead, they consist of periodic oscillations of electrical and magnetic fields originally generated by charged particles, and can therefore travel through a vacuum. These types of waves vary in wavelength, and include radio waves, microwaves, infrared radiation, visible light, ultraviolet radiation, X-rays, and gamma rays.</p><p>Waves are described by a wave equation which sets out how the disturbance proceeds over time. The mathematical form of this equation varies depending on the type of wave. Further, the behavior of particles in quantum mechanics are described by waves. In addition, gravitational waves also travel through space, which are a result of a vibration or movement in gravitational fields.</p><p>A <strong>wave</strong> can be transverse or longitudinal. Transverse waves occur when a disturbance creates oscillations that are perpendicular to the propagation of energy transfer. Longitudinal waves occur when the oscillations are parallel to the direction of energy propagation. While mechanical waves can be both transverse and longitudinal, all electromagnetic waves are transverse in free space.</p>",
    };

}
