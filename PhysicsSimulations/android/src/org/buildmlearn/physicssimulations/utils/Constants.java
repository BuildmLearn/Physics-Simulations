package org.buildmlearn.physicssimulations.utils;

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

/**
 * The <code>Constants</code> class defines the constants values and some useful methods
 * @author  Costin Giorgian
 */
public class Constants {
    public static final List<Simulation> SIMULATION_LIST = new ArrayList<Simulation>(){
        {
            add(new Simulation("Lens",
                    "Simulation Details"));
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

    /**
     *
     * @param name  The string name of the simulation
     * @return      A new instance of the simulation given by name
     */
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

    /**
     *
     * @param name  The string name of the simulation
     * @return      The id of the simulation given by name
     */
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

    /**
     *
     * @param name  The string name of the simulation
     * @return      The drawable resource of the icon for given simulation name
     */
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
            //Atwood
            "The Atwood machine (or Atwood's machine) was invented in 1784 by the English mathematician George Atwood as a laboratory experiment to verify the mechanical laws of motion with constant acceleration. Atwood's machine is a common classroom demonstration used to illustrate principles of classical mechanics.The ideal Atwood Machine consists of two objects of mass m1 and m2, connected by an inextensible massless string over an ideal massless pulley.When m1 = m2, the machine is in neutral equilibrium regardless of the position of the weights.When m1 ≠ m2 both masses experience uniform acceleration.<h3>Equation for constant acceleration</h3>We are able to derive an equation for the acceleration by using force analysis. If we consider a massless, inextensible string and an ideal massless pulley, the only forces we have to consider are: tension force (T), and the weight of the two masses (W1 and W2). To find an acceleration we need to consider the forces affecting each individual mass. Using Newton's second law (with a sign convention of \\(m_{1}>m_{2}\\) we can derive a system of equations for the acceleration (a).As a sign convention, we assume that a is positive when downward for \\(m_{1}\\) and upward for \\(m_{2}\\). Weight of \\(m_{1}\\) and \\(m_{2}\\) is simply \\( W_{1}=m_{1}g\\) and \\(W_{2}=m_{2}g\\) respectively.Forces affecting m1:$$\\;m_{1}g-T=m_{1}a$$Forces affecting m2:$$\\;T-m_{2}g=m_{2}a$$and adding the two previous equations we obtain$$\\;m_{1}g-m_{2}g=m_{1}a+m_{2}a,$$and our concluding formula for acceleration$$ a=g{m_{1}-m_{2} \\over m_{1}+m_{2}}$$Conversely, the acceleration due to gravity, g, can be found by timing the movement of the weights, and calculating a value for the uniform acceleration a: $$ a={\\frac {2d}{t^{2}}}$$ The Atwood machine is sometimes used to illustrate the Lagrangian method of deriving equations of motion. <h3>Equation for tension</h3>It can be useful to know an equation for the tension in the string. To evaluate tension, substitute the equation for acceleration in either of the 2 force equations.$$a=g{m_{1}-m_{2} \\over m_{1}+m_{2}}$$For example substituting into $$m_{1}a=m_{1}g-T$$ results in$$T={2gm_{1}m_{2} \\over m_{1}+m_{2}}={2g \\over 1/m_{1}+1/m_{2}}$$",
            //Circuit
            "<h3>Kirchhoff's current law (KCL)</h3>This law is also called Kirchhoff's first law, Kirchhoff's point rule, or Kirchhoff's junction rule (or nodal rule).The principle of conservation of electric charge implies that:At any node (junction) in an electrical circuit, the sum of currents flowing into that node is equal to the sum of currents flowing out of that nodeor equivalentlyThe algebraic sum of currents in a network of conductors meeting at a point is zero.Recalling that current is a signed (positive or negative) quantity reflecting direction towards or away from a node, this principle can be stated as:$$\\sum _{k=1}^{n}{I}_{k}=0$$n is the total number of branches with currents flowing towards or away from the node.This formula is valid for complex currents:$$\\sum _{k=1}^{n}{\\tilde {I}}_{k}=0$$The law is based on the conservation of charge whereby the charge (measured in coulombs) is the product of the current (in amperes) and the time (in seconds).<h3>Kirchhoff's voltage law (KVL)</h3>This law is also called Kirchhoff's second law, Kirchhoff's loop (or mesh) rule, and Kirchhoff's second rule.The principle of conservation of energy implies thatThe directed sum of the electrical potential differences (voltage) around any closed network is zero, or:More simply, the sum of the emfs in any closed loop is equivalent to the sum of the potential drops in that loop, or:The algebraic sum of the products of the resistances of the conductors and the currents in them in a closed loop is equal to the total emf available in that loop.Similarly to KCL, it can be stated as:$$\\sum _{k=1}^{n}V_{k}=0$$Here, n is the total number of voltages measured. The voltages may also be complex:$$\\sum _{k=1}^{n}{\\tilde {V}}_{k}=0$$This law is based on the conservation of energy whereby voltage is defined as the energy per unit charge. The total amount of energy gained per unit charge must be equal to the amount of energy lost per unit charge, as energy and charge are both conserved.",
            //Collision
            "<h3>Elastic collision</h3>Consider two particles, denoted by subscripts 1 and 2. Let m1 and m2 be the masses, u1 and u2 the velocities before collision, and v1 and v2 the velocities after collision.The conservation of the total momentum demands that the total momentum before the collision is the same as the total momentum after the collision, and is expressed by the equation $$\\,\\!m_{1}{\\vec {u}}_{1}+m_{2}{\\vec {u}}_{2}=m_{1}{\\vec {v}}_{1}+m_{2}{\\vec {v}}_{2}$$ Likewise, the conservation of the total kinetic energy is expressed by the equation $${\\frac {m_{1}u_{1}^{2}}{2}}+{\\frac {m_{2}u_{2}^{2}}{2}}={\\frac {m_{1}v_{1}^{2}}{2}}+{\\frac {m_{2}v_{2}^{2}}{2}}$$ These equations may be solved directly to find vi when ui are known or vice versa. An alternative solution is to first change the frame of reference such that one of the known velocities is zero. The unknown velocities in the new frame of reference can then be determined and followed by a conversion back to the original frame of reference to reach the same result. Once one of the unknown velocities is determined, the other can be found by symmetry.<h3>Inelastic collision</h3>A perfectly inelastic collision occurs when the maximum amount of kinetic energy of a system is lost. In a perfectly inelastic collision, i.e., a zero coefficient of restitution, the colliding particles stick together. In such a collision, kinetic energy is lost by bonding the two bodies together. This bonding energy usually results in a maximum kinetic energy loss of the system. It is necessary to consider conservation of momentum: (Note: In the sliding block example above, momentum of the two body system is only conserved if the surface has zero friction. With friction, momentum of the two bodies is transferred to the surface that the two bodies are sliding upon. Similarly, if there is air resistance, the momentum of the bodies can be transferred to the air.) The equation below holds true for the two-body (Body A, Body B) system collision in the example above. In this example, momentum of the system is conserved because there is no friction between the sliding bodies and the surface.$$m_a u_a + m_b u_b = \\left( m_a + m_b \\right)  v \\$$where v is the final velocity, which is hence given by$$v=\\frac{m_a u_a + m_b  u_b}{m_a + m_b}$$",
            //Lens
            "Ray diagrams can be used to determine the image location, size, orientation and type of image formed of objects when placed at a given location in front of a lens. The use of these diagrams was demonstrated earlier in Lesson 5 for both converging and diverging lenses. Ray diagrams provide useful information about object-image relationships, yet fail to provide the information in a quantitative form. While a ray diagram may help one determine the approximate location and size of the image, it will not provide numerical information about image distance and image size. To obtain this type of numerical information, it is necessary to use the Lens Equation and the Magnification Equation. The lens equation expresses the quantitative relationship between the object distance \\(d_o\\), the image distance \\(d_i\\), and the focal length \\(f\\). The equation is stated as follows:$$ \\frac{1}{f} = \\frac{1}{d_o} + \\frac{1}{d_i} $$The magnification equation relates the ratio of the image distance and object distance to the ratio of the image height \\(h_i\\) and object height \\(h_o\\). The magnification equation is stated as follows:$$ M = \\frac{h_i}{h_o} = \\frac{d_i}{d_o} $$These two equations can be combined to yield information about the image distance and image height if the object distance, object height, and focal length are known.",
            //Interference
            "A long slit of infinitesimal width which is illuminated by light diffracts the light into a series of circular waves and the wavefront which emerges from the slit is a cylindrical wave of uniform intensity.A slit which is wider than a wavelength produces interference effects in the space downstream of the slit. These can be explained by assuming that the slit behaves as though it has a large number of point sources spaced evenly across the width of the slit. The analysis of this system is simplified if we consider light of a single wavelength. If the incident light is coherent, these sources all have the same phase. Light incident at a given point in the space downstream of the slit is made up of contributions from each of these point sources and if the relative phases of these contributions vary by 2π or more, we may expect to find minima and maxima in the diffracted light. Such phase differences are caused by differences in the path lengths over which contributing rays reach the point from the slit.We can find the angle at which a first minimum is obtained in the diffracted light by the following reasoning. The light from a source located at the top edge of the slit interferes destructively with a source located at the middle of the slit, when the path difference between them is equal to λ/2. Similarly, the source just below the top of the slit will interfere destructively with the source located just below the middle of the slit at the same angle. We can continue this reasoning along the entire height of the slit to conclude that the condition for destructive interference for the entire slit is the same as the condition for destructive interference between two narrow slits a distance apart that is half the width of the slit. The path difference is given by $${\\frac {d\\sin(\\theta )}{2}}$$ so that the minimum intensity occurs at an angle θmin given by$$ d\\,\\sin \\theta _{\\text{min}}=\\lambda $$whered is the width of the slit,$$\\theta _{\\text{min}} $$is the angle of incidence at which the minimum intensity occurs, and\\(\\lambda\\)  is the wavelength of the lightA similar argument can be used to show that if we imagine the slit to be divided into four, six, eight parts, etc., minima are obtained at angles θn given by$$ d\\,\\sin \\theta _{n}=n\\lambda $$where n is an integer other than zero.",
            //Refraction
            "<p><b>Refraction</b> is the change in direction of propagation of a wave due to a change in its transmission medium.</p><p>The phenomenon is explained by the conservation of energy and the conservation of momentum. Due to the change of medium, the phase velocity of the wave is changed but its frequency remains constant. This is most commonly observed when a wave passes from one medium to another at any angle other than 0° from the normal. Refraction of light is the most commonly observed phenomenon, but any type of wave can refract when it interacts with a medium, for example when sound waves pass from one medium into another or when water waves move into water of a different depth. Refraction is described by Snell's law, which states that, for a given pair of media and a wave with a single frequency, the ratio of the sines of the angle of incidence <i>θ<sub>1</sub></i> and angle of refraction <i>θ<sub>2</sub></i> is equivalent to the ratio of phase velocities (<i>v</i><sub>1</sub> / <i>v</i><sub>2</sub>) in the two media, or equivalently, to the opposite ratio of the indices of refraction (<i>n</i><sub>2</sub> / <i>n</i><sub>1</sub>):</p><p><strong>sinθ<sub>1</sub>/sinθ<sub>2</sub>=n<sub>2</sub> / n<sub>1</sub></strong></p>",
            //Pendulum
            "<p>A pendulum is a weight suspended from a pivot so that it can swing freely. When a pendulum is displaced sideways from its resting, equilibrium position, it is subject to a restoring force due to gravity that will accelerate it back toward the equilibrium position. When released, the restoring force combined with the pendulum's mass causes it to oscillate about the equilibrium position, swinging back and forth. The time for one complete cycle, a left swing and a right swing, is called the period. The period depends on the length of the pendulum and also to a slight degree on the amplitude, the width of the pendulum's swing.</p><h2>Factors and parameters</h2><p>The major factor involved in the equations for calculating the frequency of a pendulum is the length of the rod or wire, provided the initial angle or amplitude of the swing is small. The mass or weight of the bob is <b>not</b> a factor in the frequency of the simple pendulum, but the acceleration of gravity is in the equation.</p><p>Knowing the length of the pendulum, you can determine its frequency. Or, if you want a specific frequency, you can determine the necessary length.</p><h2>Equations for period and frequency</h2><p>The period of the motion for a pendulum is how long it takes to swing back-and-forth, measured in seconds. Period is designated as <b>T</b>.</p><p>The frequency of a pendulum is how many back-and-forth swings there are in a second, measured in hertz. Frequency is usually designated as <b>f</b>.</p><p>The period <b>T</b> is the reciprocal of the frequency. <b>T = 1/f</b> and <b>f = 1/T</b>.</p><h3>Period equation</h3><p>The equation for the period of a simple pendulum starting at a small angle (<b>a</b>) is:</p><p><b>T = 2&#960;&radic;(L/g)</b></p><p>where:</p><b>T</b> is the period in seconds (s)<br><b>&#960;</b> = 3.14<br><b>L</b> is the length of the rod or wire in meters or feet<br><b>g</b> is the acceleration due to gravity (9.8 m/s&sup2; or 32 ft/s&sup2;)<h3>Frequency equation</h3><p>The equation to calculate the frequency of a simple pendulum, starting at a small angle is:</p><p><b>f =[ &radic;(g/L)]/2&pi;</b></p><p>where:</p><b>f</b> = frequency in cycles per second (Hertz or Hz)<br><b>g</b> is the acceleration due to gravity (9.8 m/s&sup2; or 32 ft/s&sup2;)<br><b>L</b> is the length of the rod or wire in meters or feet<h2>Length of wire</h2><p>If you wanted to find the length of the rod or wire for a given frequency, take the equation <b>f = &radic;(g/L)/2&pi;</b> and solve for <b>L</b>. The result is:</p><p><b>L = g/(4&#960;<sup>2</sup>f<sup>2</sup>)</b></p><p>where:</p><b>&#960;<sup>2</sup></b> is pi-squared or <b>pi</b> times <b>pi</b><br><b>f<sup>2</sup></b> is frequency-squared or <b>f</b> times <b>f</b><br><b>4&#960;<sup>2</sup>f<sup>2</sup></b> is 4 times <b>&#960;<sup>2</sup></b> times <b>f<sup>2</sup></b><h2>Velocity</h2><p>Although the velocity of the bob at the bottom of the swing is not a factor in determining frequency, it may be of interest in other calculations.</p><p>The velocity can be approximated from the gravity equation for a weight dropping from a height. The height is determined by the angle from the vertical that is the starting point of the pendulum's swing. Thus, the velocity at the bottom of the swing is:</p><p><b>v = &radic;{2gL[1-cos(θ)]} </b></p><p>where:</p><b>v</b> is the velocity of the weight at the bottom of the swing<br><b>g</b> is the acceleration due to gravity<br><b>L</b> is the length of the wire<br><b>θ</b> is the angle from the vertical<br><b>cos(θ)</b> is the cosine of angle <b>θ</b><p><b>Note</b> that the velocity, as well as the period and frequency are affected by the acceleration due to gravity constant. In other words, a pendulum will swing slower on the Moon than on the Earth, because the gravity on the Moon is less than on the Earth.</p><h3>Energy in a Pendulum</h3><p>In a simple pendulum with no friction, mechanical energy is conserved.  Total mechanical energy is a combination of kinetic energy and gravitational potential energy.   As the pendulum swings back and forth, there is a constant exchange between kinetic energy and gravitational potential energy.</p><h1>Potential Energy</h1><p>The potential energy of the pendulum can be modeled off of the basic equation <strong></strong></p><p><strong>PE = mgh</strong></p><p>where <strong>g</strong> is the acceleration due to gravity and <strong>h</strong> is the height.  We often use this equation to model objects in free fall.</p><p>However, the pendulum is constrained by the rod or string and is not in free fall.  Thus we must express the height in terms of <strong>θ</strong>, the angle and <strong>L,</strong> the length of the pendulum.   Thus <strong>h = L(1 – cos(θ))</strong></p><p>At all points in-between the potential energy can be described using</p><p><strong>PE = mgL(1 – cos(θ))</strong></p><h1>Kinetic Energy</h1><p>Ignoring friction and other non-conservative forces, we find that in a simple pendulum, mechanical energy is conserved.   The kinetic energy would be <strong>KE=  ½mv<sup>2</sup></strong>,where <strong>m</strong> is the mass of the pendulum, and <strong>v </strong>is the speed of the pendulum.</p><p>At its highest point the pendulum is momentarily motionless.  All of the energy in the pendulum is gravitational potential energy and there is no kinetic energy. At the lowest point the pendulum has its greatest speed. All of the energy in the pendulum is kinetic energy and there is no gravitational potential energy.  However, the total energy is constant as a function of time.<p>If there is friction, we have a damped pendulum which exhibits damped harmonic motion. All of the mechanical energy eventually becomes other forms of energy such as heat or sound.</p><h1>Mass and the Period</h1><p>Your investigations should have found that mass does not affect the period  of a pendulum.  One reason to explain this is using conservation of energy.</p><p>If we examine the equations for conservation of energy in a pendulum system we find that mass cancels out of the equations.</p><p>KE<sub>i</sub> + PE<sub>i</sub>= KE<sub>f</sub>+PE<sub>f</sub></p><p>[½mv<sup>2</sup> + mgL(1-cos(θ)) ]<sub>i</sub> =  [½mv<sup>2</sup> + mgL(1-cos(θ)) ]<sub>f</sub></p><p>There is a direct relationship between the angle <strong>θ</strong> and the velocity.  Because of this, the mass does not affect the  behavior of the pendulum and does not alter the period of the pendulum.</p>",
            //Projectile
            "<h3>The initial velocity</h3>Let the projectile be launched with an initial velocity \\({v}_{0}\\) which can be expressed as the sum of horizontal and vertical components as follows:$${\\mathbf  {v}}_{0}=v_{{0x}} {\\mathbf  {i}}+v_{{0y}}{\\mathbf  {j}}$$The components \\(v_{{0x}}\\) and \\(v_{{0y}}\\) can be found if the initial launch angle, \\(\\theta\\) , is known:$$v_{{0x}}=v_{0}\\cos \\theta$$$$v_{{0y}}=v_{0}\\sin \\theta$$<h3>Time of flight or total time of the whole journey</h3>The total time \\(t\\) for which the projectile remains in the air is called the time of flight.$$y=v_{0}t\\sin(\\theta )-{\\frac  {1}{2}}gt^{2}$$After the flight, the projectile returns to the horizontal axis, so y=0$$0=v_{0}t\\sin(\\theta )-{\\frac  {1}{2}}gt^{2}$$$$v_{0}t\\sin(\\theta )={\\frac  {1}{2}}gt^{2}$$$$ v_{0}\\sin(\\theta )={\\frac  {1}{2}}gt$$$$ t={\\frac  {2v_{0}\\sin(\\theta )}{g}}$$Note that we have neglected air resistance on the projectile.",
            //Spring
            "<p>Consider a simple helical spring that has one end attached to some fixed object, while the free end is being pulled by a force whose magnitude is F. Suppose that the spring has reached a state of equilibrium, where its length is not changing anymore. <p>Let X be the amount by which the free end of the spring was displaced from its \"relaxed\" position (when it is not being stretched). Hooke's law states that <p>$$F = k x$$ <p>or, equivalently, <p>$$X = \\frac{F}{k}$$ <p> where k is a positive real number, characteristic of the spring. Moreover, the same formula holds when the spring is compressed, with F and X both negative in that case. According to this formula, the graph of the applied force F as a function of the displacement X will be a straight line passing through the origin, whose slope is k. Hooke's law for a spring is often stated under the convention that F is the restoring (reaction) force exerted by the spring on whatever is pulling its free end. In that case the equation becomes <p> $$F = -kX$$ <p>since the direction of the restoring force is opposite to that of the displacement.",
            //Wave
            "<p>In physics, a <strong>wave</strong> is an oscillation accompanied by a transfer of energy that travels through medium (space or mass). Frequency refers to the addition of time. Wave motion transfers energy from one point to another, which displace particles of the transmission medium — that is, with little or no associated mass transport. Waves consist, instead, of oscillations or vibrations (of a physical quantity), around almost fixed locations.</p><p>There are two main types of waves. Mechanical waves propagate through a medium, and the substance of this medium is deformed. The deformation reverses itself owing to restoring forces resulting from its deformation. For example, sound waves propagate via air molecules colliding with their neighbors. When air molecules collide, they also bounce away from each other (a restoring force). This keeps the molecules from continuing to travel in the direction of the wave.The second main type of wave, electromagnetic waves, do not require a medium. Instead, they consist of periodic oscillations of electrical and magnetic fields originally generated by charged particles, and can therefore travel through a vacuum. These types of waves vary in wavelength, and include radio waves, microwaves, infrared radiation, visible light, ultraviolet radiation, X-rays, and gamma rays.</p><p>Waves are described by a wave equation which sets out how the disturbance proceeds over time. The mathematical form of this equation varies depending on the type of wave. Further, the behavior of particles in quantum mechanics are described by waves. In addition, gravitational waves also travel through space, which are a result of a vibration or movement in gravitational fields.</p><p>A <strong>wave</strong> can be transverse or longitudinal. Transverse waves occur when a disturbance creates oscillations that are perpendicular to the propagation of energy transfer. Longitudinal waves occur when the oscillations are parallel to the direction of energy propagation. While mechanical waves can be both transverse and longitudinal, all electromagnetic waves are transverse in free space.</p>",
    };

}
