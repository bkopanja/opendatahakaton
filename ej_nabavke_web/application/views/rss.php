<?php  echo '<?xml version="1.0" encoding="' . $encoding . '"?>' . "\n"; ?>

<rss version="2.0"
     xmlns:dc="http://purl.org/dc/elements/1.1/"
     xmlns:sy="http://purl.org/rss/1.0/modules/syndication/"
     xmlns:admin="http://webns.net/mvcb/"
     xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
     xmlns:content="http://purl.org/rss/1.0/modules/content/">

    <channel>

        <title><?php echo $feed_name; ?></title>

        <link><?php echo $feed_url; ?></link>
        <description><?php echo $page_description; ?></description>
        <dc:language><?php echo $page_language; ?></dc:language>
        <dc:creator><?php echo $creator_email; ?></dc:creator>

        <dc:rights>Copyright <?php echo gmdate("Y", time()); ?></dc:rights>
        <admin:generatorAgent rdf:resource="http://www.bojankopanja.com/" />

        <?php foreach($posts->result() as $post): ?>

            <item>

                <title><![CDATA[ <?php echo mb_convert_case($post->naziv_dokumenta, MB_CASE_TITLE, 'UTF-8'); ?> ]]></title>
                <link><?php echo $post->link ?></link>

                <description><?php echo xml_convert(mb_convert_case($post->predmet_nabavke, MB_CASE_TITLE, 'UTF-8')); ?></description>
                <pubDate><?php echo $post->datum_poslednje_izmene; ?></pubDate>
            </item>


        <?php endforeach; ?>

    </channel>
    <</rss>