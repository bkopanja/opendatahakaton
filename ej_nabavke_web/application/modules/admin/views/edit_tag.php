<h3>Edit tag: <?= $tag->tag ?></h3>

<div class="row">
  <span class="span12">

    <form action="<?= base_url() ?>admin/edit_tag/<?= $tag->id ?>/<?= $tag->kategorija_id ?>" method="post">
  
      <legend>Tag info:</legend>
  
      <fieldset>
        <label>Tag: </label>
        <input type="text" placeholder="Tag" name="name" value="<?= $tag->tag ?>" />
      </fieldset>
    
      <button type="submit" class="btn btn-primary">Save</button>
    
    </form>
    
  </span>
</div>