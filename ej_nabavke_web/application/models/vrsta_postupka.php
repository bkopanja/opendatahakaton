<?php

class vrsta_postupka extends CI_Model {

    function getPurchaseTypes() {
        $query_sql = 'SELECT * FROM vrste_postupaka ORDER BY naziv;';
        $query = $this->db->query($query_sql);
        return $query->result_object();
    }

}