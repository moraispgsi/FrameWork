
package utils.implementations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface usada para utilidades de serialização
 *
 * @author Ricardo José Horta Morais
 */
public interface UtilsSerializacao {

    /**
     * Serializa um objeto e devolve-o num array de bytes
     *
     * @param objeto objeto a serializar
     * @return objeto serializado em array de bytes
     */
    public static byte[] serializar(Object objeto) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(b)) {

            o.writeObject(objeto);

            return b.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(UtilsSerializacao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível serializar dados.");
        }
    }

    /**
     * Deserializa um array de bytes e devolve o objeto
     *
     * @param <T> tipo do objeto a deserializar
     * @param tipo tipo do objeto a deserializar
     * @param dados dados do objeto
     * @return objeto deserializado
     */
    public static <T> T deserializar(Class<T> tipo, byte[] dados) {

        ByteArrayInputStream b = new ByteArrayInputStream(dados);
        try (ObjectInputStream o = new ObjectInputStream(b)) {
            T object = (T) o.readObject();
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(UtilsSerializacao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Não foi possível deserializar dados.");
        }

    }

    /**
     * Serializa um objeto e de seguida deserializa criando assim uma cópia
     *
     * @param <T> tipo do objeto
     * @param objeto objeto a copiar
     * @return copia do objeto
     */
    public static <T> T copiaSerializada(T objeto) {

        byte[] dados = UtilsSerializacao.serializar(objeto);
        return UtilsSerializacao.deserializar((Class<T>) objeto.getClass(), dados);

    }

}
