<?php

class opstina extends CI_Model {

    function getMuncipalities() {
        $query_sql = 'SELECT * FROM opstine ORDER BY naziv;';
        $query = $this->db->query($query_sql);
        return $query->result_object();
    }

}