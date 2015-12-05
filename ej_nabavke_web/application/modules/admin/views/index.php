<h3>Categories:</h3>

<table class="table">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Actions</th>
  </tr>
  
  <?php $i=1; foreach($categories as $c) { ?>
  <tr>
    <td><?= $c->id ?></td>
    <td><?= $c->naziv ?></td>
    <td><a href="<?= base_url() ?>admin/edit_category/<?= $c->id ?>"><i class="icon-pencil"></i></a>&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <?php } ?>
  
</table>

<script type="text/javascript">
  function confirm_toggle() {
    return confirm("Are you sure you want to toggle place's state?");
  }
</script>