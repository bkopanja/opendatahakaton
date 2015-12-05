<?php

class admin extends MX_Controller {

    function __construct() {

        $this->load->library('tank_auth');
        $this->load->helper('security');

        if (!$this->tank_auth->is_logged_in()) {
            redirect(base_url() . 'auth/login/');
        }

    }

    function index() {

        $this->load->view("header_logged");

        $this->load->model("kategorija");
        $data['categories'] = $this->kategorija->getCategories();

        $this->load->view("admin/index", $data);

        $this->load->view("footer_logged");
    }

    function edit_category($id=0) {
        if($id == 0)
            redirect(base_url()."admin");

        $this->load->model("kategorija");

        if(count($_POST) > 0) {
            $this->kategorija->update($id, $this->input->post("name"));

            redirect(base_url()."admin");
        } else {
            $this->load->view("header_logged");

            $category = $this->kategorija->getCategory($id);

            $this->load->model("tag");
            $tags = $this->tag->loadForCategory($category->id);

            $data['category'] = $category;
            $data['tags'] = $tags;

            $this->load->view("admin/edit_category", $data);

            $this->load->view("footer_logged");
        }
    }

    function delete_tag($id=0, $categoryId)
    {
        if($id == 0)
            redirect(base_url()."admin");

        $this->load->model("tag");
        $this->tag->delete($id);

        redirect(base_url()."admin/edit_category/".$categoryId);
    }

    function new_tag($category_id=0) {
        if($category_id == 0)
            redirect(base_url()."admin");

        $this->load->model("tag");

        if(count($_POST) > 0) {
            $this->tag->add($category_id, $this->input->post("name"));

            redirect(base_url()."admin/edit_category/$category_id");
        } else {
            $this->load->view("header_logged");

            $this->load->model("kategorija");
            $category = $this->kategorija->getCategory($category_id);
            $data['category'] = $category;

            $this->load->view("admin/add_tag", $data);

            $this->load->view("footer_logged");
        }
    }

    function edit_tag($tag_id=0, $category_id) {
        if($tag_id == 0)
            redirect(base_url()."admin");

        $this->load->model("tag");

        if(count($_POST) > 0) {
            $this->tag->update($tag_id, $this->input->post("name"));

            redirect(base_url()."admin/edit_category/$category_id");
        } else {
            $this->load->view("header_logged");

            $tag = $this->tag->get($tag_id);
            $data['tag'] = $tag;

            $this->load->view("admin/edit_tag", $data);

            $this->load->view("footer_logged");
        }
    }

}