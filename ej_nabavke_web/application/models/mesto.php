<?php

class mesto extends CI_Model {

    function getPlaces() {
        $query_sql = 'SELECT mesta.*, count(nabavke.id) as broj_nabavki
                      FROM mesta
                      LEFT JOIN nabavke
                      ON mesta.id = nabavke.mesto_id
                      GROUP BY mesta.id
                      HAVING broj_nabavki > 0
                      ORDER BY mesta.naziv;';
        $query = $this->db->query($query_sql);
        return $query->result_object();
    }



}