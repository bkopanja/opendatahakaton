<a style="float: right;" href="<?= base_url() ?>admin/new_tag/<?= $category->id ?>"><button class="btn btn-primary">Add New Tag for Category</button></a>

<h3>Edit Place: <?= $category->naziv ?></h3>

<div class="row">
  <span class="span4">

    <form action="<?= base_url() ?>admin/edit_category/<?= $category->id ?>" method="post">
  
      <legend>Category info:</legend>
  
      <fieldset>
        <label>Category Name: </label>
        <input type="text" placeholder="Place name" name="name" value="<?= $category->naziv ?>" />
      </fieldset>
    
      <button type="submit" class="btn btn-primary">Save</button>
    
    </form>
    
  </span>
  
  <span class="span8">
    <legend>Tags:</legend>
    <table class="table">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
      </tr>
      
      <?php $i=1; foreach($tags as $tag) { ?>
      <tr>
        <td><?= $tag->id ?></td>
        <td><?= $tag->tag ?></td>
        <td>
        <a href="<?= base_url() ?>admin/edit_tag/<?= $tag->id ?>/<?= $category->id ?>"><i class="icon-pencil"></i></a>&nbsp;&nbsp;&nbsp;
        <a href="<?= base_url() ?>admin/delete_tag/<?= $tag->id ?>/<?= $category->id ?>" onclick="return confirm_delete();"><i class="icon-remove"></i></a>
        </td>
      </tr>
      <?php } ?>
      
    </table>
  </span>
  
</div>

<script type="text/javascript">
  function confirm_delete() {
    return confirm('Are you sure you want to delete this tag?');
  }
</script>