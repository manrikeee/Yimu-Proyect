<?php
/**
 * Insertar nueva actividad
 */

require 'Actividad.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = Actividad::insert(
        $body['deporte'],
        $body['fecha'],
        $body['hora'],
        $body['id_usuario'],
        $body['id_pista'],
        $body['max_personas'],
        $body['plazas_disponibles'],
        $body['estado']);

    if ($retorno) {
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Creacion correcta"));
		echo $json_string;
    } else {
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se creo el registro"));
		echo $json_string;
    }
}

?>