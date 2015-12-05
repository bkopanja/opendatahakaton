<?php

class kategorija extends CI_Model {

    function getCategories() {
        $query_sql = 'SELECT * FROM kategorije ORDER BY id;';
        $query = $this->db->query($query_sql);
        return $query->result_object();
    }

    function getCategory($id) {
        $query_sql = 'SELECT * FROM kategorije WHERE id = ?;';
        $query = $this->db->query($query_sql, array($id));
        if ($query->num_rows() > 0) {
            return $query->row();
        }

        return null;
    }

    function update($id, $name) {

        $sql = "UPDATE kategorije SET";

        if(!is_null($name)) {
            $sql .= " naziv = '$name'";
        }

        $sql .= " WHERE id = {$id}";

        return $this->db->query($sql);

    }

}