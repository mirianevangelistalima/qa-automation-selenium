package data;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class UsuariosData {

    public static Stream<Arguments> usuariosValidos() {
        return Stream.of(
                Arguments.of(UserData.STANDARD_USER, UserData.PASSWORD),
                Arguments.of(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD),
                Arguments.of(UserData.VISUAL_USER, UserData.PASSWORD)
        );
    }

    public static Stream<Arguments> usuariosProblematicos() {
        return Stream.of(
                Arguments.of(UserData.PROBLEM_USER, UserData.PASSWORD),
                Arguments.of(UserData.ERROR_USER, UserData.PASSWORD)
        );
    }

    public static Stream<Arguments> todosUsuarios() {
        return Stream.of(
                Arguments.of(UserData.STANDARD_USER, UserData.PASSWORD),
                Arguments.of(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD),
                Arguments.of(UserData.VISUAL_USER, UserData.PASSWORD),
                Arguments.of(UserData.PROBLEM_USER, UserData.PASSWORD),
                Arguments.of(UserData.ERROR_USER, UserData.PASSWORD)
        );
    }

    public static Stream<Arguments> usuariosQueAbremDetalheCorreto() {
        return Stream.of(
                Arguments.of(UserData.STANDARD_USER, UserData.PASSWORD),
                Arguments.of(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD),
                Arguments.of(UserData.VISUAL_USER, UserData.PASSWORD),
                Arguments.of(UserData.ERROR_USER, UserData.PASSWORD)
        );
    }
    public static Stream<Arguments> usuariosQueValidamCorretamente(){
        return Stream.of(
                Arguments.of(UserData.STANDARD_USER, UserData.PASSWORD),
                Arguments.of(UserData.PERFORMANCE_GLITCH_USER, UserData.PASSWORD),
                Arguments.of(UserData.VISUAL_USER, UserData.PASSWORD),
                Arguments.of(UserData.PROBLEM_USER, UserData.PASSWORD)
                );

    }

    public static Stream<Arguments> usuariosProblemUser() {
        return Stream.of(
                Arguments.of(UserData.PROBLEM_USER, UserData.PASSWORD)
        );
    }

    public static Stream<Arguments> usuariosError() {
        return Stream.of(
                Arguments.of(UserData.ERROR_USER, UserData.PASSWORD)
        );
    }




}
