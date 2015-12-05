<?php

class tag extends CI_Model {

    function loadForCategory($categoryId) {
        $query_sql = 'SELECT * FROM tagovi WHERE kategorija_id = ? ORDER BY tag;';
        $query = $this->db->query($query_sql, array($categoryId));
        return $query->result_object();
    }

    function get($id) {
        $query_sql = 'SELECT * FROM tagovi WHERE id = ?;';
        $query = $this->db->query($query_sql, array($id));
        if ($query->num_rows() > 0) {
            return $query->row();
        }

        return null;
    }

    function add($categoryId, $tag) {
        $query_sql = 'INSERT INTO tagovi(tag, kategorija_id) VALUES(?, ?);';
        return $this->db->query($query_sql, array($tag, $categoryId));
    }

    function update($id, $tag) {
        $sql = "UPDATE tagovi SET";
        $sql .= " tag = '$tag'";
        $sql .= " WHERE id = {$id}";

        return $this->db->query($sql);

    }

    function delete($id) {
        $query_sql = 'DELETE FROM tagovi WHERE id = ?;';
        return $this->db->query($query_sql, array($id));
    }

}