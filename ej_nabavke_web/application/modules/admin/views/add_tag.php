<h3>New tag for category: <?= $category->naziv ?></h3>

<div class="row">
  <span class="span12">

    <form action="<?= base_url() ?>admin/new_tag/<?= $category->id ?>" method="post">
  
      <legend>Tag info:</legend>
  
      <fieldset>
        <label>Tag: </label>
        <input type="text" placeholder="Tag" name="name" />
      </fieldset>
    
      <button type="submit" class="btn btn-primary">Save</button>
    
    </form>
    
  </span>
</div>

<script type="text/javascript">
  function confirm_delete() {
    return confirm('Are you sure you want to delete this tag?');
  }
</script>