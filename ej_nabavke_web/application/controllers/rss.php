<?php

class rss extends MX_Controller {

    function __construct() {

        $this->load->helper('xml');
        $this->load->model('nabavka');
    }

    function index() {

        $data['feed_name'] = 'Ej Nabavke.com';
        $data['encoding'] = 'utf-8';
        $data['feed_url'] = 'http://www.ejnabavke.com/rss';
        $data['page_description'] = 'Spisak javnih nabavki ';
        $data['page_language'] = 'en-en';
        $data['creator_email'] = 'kopanja@gmail.com';
        $data['posts'] = $this->nabavka->getMapaPristupacnosti();
        header("Content-Type: application/rss+xml");

        $this->load->view('rss', $data);
    }

}