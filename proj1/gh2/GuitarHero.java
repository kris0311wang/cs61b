package gh2;

import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int NUM_KEYS = 37;
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[NUM_KEYS];

        // Initialize the GuitarString array
        for (int i = 0; i < NUM_KEYS; i++) {
            double frequency = CONCERT_A * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {
            // Check if a key has been pressed
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);

                // If the key is valid, pluck the corresponding string
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            // Compute the superposition of samples
            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }

            // Play the sample on standard audio
            StdAudio.play(sample);

            // Advance the simulation of each guitar string by one step
            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}