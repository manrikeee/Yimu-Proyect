<?php
require 'Centros.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['localidad'])) {

        // Obtener parámetro idalumno
        $localidad = $_GET['localidad'];
        
        // Tratar retorno
        $retorno = Centros::getByLocalidad($localidad);


        if ($retorno) {

            //$alumno["estado"] = 1;		// cambio "1" a 1 porque no coge bien la cadena.
           // $alumno["alumno"] = $retorno;
            // Enviar objeto json del alumno
            print json_encode($retorno);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}