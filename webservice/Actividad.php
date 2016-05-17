<?php

/**
 * Representa el la estructura de las Alumnoss
 * almacenadas en la base de datos
 */
require 'Database.php';

class Usuarios
{
    function __construct()
    {
    }

    /**
     * Retorna en la fila especificada de la tabla 'Alumnos'
     *
     * @param $idAlumno Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT * FROM actividad";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function getById($nombre,$password)
    {
        // Consulta de la tabla Alumnos
        $consulta = "SELECT *
                             FROM Usuario
                             WHERE nombre = ? and password=?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($nombre,$password));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idAlumno      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion nueva direccion
     
     */
    public static function update(
        $id,
        $nombre,
        $direccion
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE Usuario" .
            " SET nombre=?, direccion=? " .
            "WHERE id=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($nombre, $direccion, $id));

        return $cmd;
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insert(
                $deporte,
                $fecha,
                $hora,
                $id_usuario,
                $id_pista,
                $max_personas,
                $plazas_disponibles,
                $estado
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO actividad ( " .
            "deporte," .
            " fecha,"  .
            " hora,"  .      
            " id_usuario," .
            " id_pista," .           
            " max_personas,".
            " plazas_disponibles)" .
            " VALUES( ?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $deporte,
                $fecha,
                $hora,
                $id_usuario,
                $id_pista,
                $max_personas,
                $plazas_disponibles,
                $estado
            )
        );

    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idAlumno identificador de la tabla Alumnos
     * @return bool Respuesta de la eliminación
     */
    public static function delete($id)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM Usuario WHERE id=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idAlumno));
    }
}

?>