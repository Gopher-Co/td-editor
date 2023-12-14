package gopherco.io.console;

import gopherco.io.StringInput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleInput implements StringInput {

    public String read() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
