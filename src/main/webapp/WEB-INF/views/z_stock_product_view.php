<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Warehouse management</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="<?php echo base_url('assets/css/jquery-ui.css'); ?>">
    <link rel="stylesheet" href="<?php echo base_url('assets/css/bootstrap.css'); ?>">
    <link rel="stylesheet" href="<?php echo base_url('assets/css/chosen.css'); ?>">
    <link rel="stylesheet" href="<?php echo base_url('assets/css/style.css"'); ?>" media="all">
    
    <script src="<?php echo base_url('assets/js/jquery-1.11.3.min.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/jquery-ui.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/jquery-ui.multidatespicker.js'); ?>"></script>
    <!-- <script src="<?php echo base_url('assets/js/modernizr-custom.min.js'); ?>"></script> -->
    <script src="<?php echo base_url('assets/js/bootstrap.min.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/chosen.jquery.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/jquery.maskedinput.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/accounting.min.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/jquery.validate.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/additional-methods.min.js'); ?>"></script>
    <script src="<?php echo base_url('assets/js/function.js'); ?>"></script>

    <script>
    $(function(){
      $('input[name="warehouse_id"]').focus();

      /* pagination */
      $('.pagination-area>a, .pagination-area>strong').addClass('btn btn-sm btn-primary');
      $('.pagination-area>strong').addClass('disabled');
    });

    function check_delete(id){
      var answer = prompt("Confirm delete?");
      if(answer){
        $('input[name="warehouse_id"]').val(id);
        $('input[name="warehouse_delete_reason"]').val(encodeURI(answer));
        $('form[name="list"]').submit();
      }else{
        return false;
      }
    }

    function login_as(id){
      $('input[name="warehouse_id"]').val(id);
      $('input[name="act"]').val('login_as');
      $('form[name="list"]').submit();
    }
    </script>
  </head>

  <body>

    <?php $this->load->view('inc/header-area.php'); ?>

    








































    <?php if($this->router->fetch_method() == 'update' || $this->router->fetch_method() == 'insert'){ ?>
    <?php } ?>

    











































    <?php if($this->router->fetch_method() == 'select'){ ?>
    <div class="content-area">

      <div class="container-fluid">
        <div class="row">

          <h2 class="col-sm-12">Warehouse Management</h2>

            <div class="content-column-area col-md-12 col-sm-12">

              <div class="fieldset min-height-500">
                <div class="row">

                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_in')?>">Stock In</a>
                    </blockquote>
                  </div>
                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_out')?>">Stock Out</a>
                    </blockquote>
                  </div>
                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_transfer')?>">Stock Transfer</a>
                    </blockquote>
                  </div>
                  <!-- <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('consignment')?>">Consignment</a>
                    </blockquote>
                  </div> -->

                </div>

                <div class="hr"></div>


                <div class="fieldset">
                  <div class="search-area">

                    <form z_stock_product="form" method="get">
                      <!-- <input type="hidden" name="z_stock_product_id" /> -->
                      <input type="hidden" name="z_stock_product_id" />
                      <table>
                        <tbody>
                          <tr>
                            <td width="90%">
                              <div class="row">
                                <div class="col-sm-2"><h6>Filters</h6></div>
                                <div class="col-sm-2">
                                  <input type="text" name="z_stock_product_id" class="form-control input-sm" placeholder="#" value="" />
                                </div>
                                <div class="col-sm-2">
                                  <input type="text" name="z_stock_product_name_like" class="form-control input-sm" placeholder="Name" value="" />
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-2"></div>
                              </div>
                            </td>
                            <td valign="top" width="10%" class="text-right">
                              <button type="submit" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Search">
                                <i class="glyphicon glyphicon-search"></i>
                              </button>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </form>

                  </div> <!-- list-container -->
                </div>
                <div class="fieldset">

                  <div class="list-area">
                    <form name="list" action="<?=base_url('z_stock_product/delete')?>" method="post">
                      <!-- <input type="hidden" name="z_stock_product_id" /> -->
                      <input type="hidden" name="z_stock_product_id" />
                      <input type="hidden" name="z_stock_product_delete_reason" />
                      <div class="page-area">
                        <span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
                        <?=$this->pagination->create_links()?>
                      </div>
                      <table id="z_stock_product" class="table table-striped table-bordered">
                        <thead>
                          <tr>
                            <th width="40">#</th>
                            <!-- <th>
                              <a href="<?=get_order_link('z_stock_product_name')?>">
                                Name <i class="glyphicon glyphicon-sort corpcolor-font"></i>
                              </a>
                            </th> -->
                            <th>
                              <a>
                                Product</i>
                              </a>
                            </th>
                            <!-- <th width="160">
                              <a href="<?=get_order_link('z_stock_product_modify')?>">
                                Modify <i class="glyphicon glyphicon-sort corpcolor-font"></i>
                              </a>
                            </th> -->
                            <?php foreach($warehouses as $key => $value){ ?> 
                            <th><?php echo $value->warehouse_name; ?></th>
                            <?php } ?>
                            <!-- <th width="40"></th> -->
                            <!-- <th width="40"></th>
                            <th width="40" class="text-right">
                              <a href="<?=base_url('z_stock_product/insert')?>" data-toggle="tooltip" title="Insert">
                                <i class="glyphicon glyphicon-plus"></i>
                              </a>
                            </th> -->
                          </tr>
                        </thead>
                        <tbody>
                          <?php foreach($stock_product as $key => $value){ ?>
                          <tr>
                            <td title="<?=$value->product_id?>"><?=$key+1?></td>
                            <!-- <td><?=ucfirst($value->z_stock_product_name)?></td> -->
                            <td><?=ucfirst($value->product_name)?></td>
                            <!-- <td><?=convert_datetime_to_date($value->z_stock_product_modify)?></td> -->
                            <!-- <td class="text-right">
                              <span data-toggle="modal" data-target="#myModal" class="modal-btn" rel="<?=$value->z_stock_product_id?>">
                                <a data-toggle="tooltip" title="More">
                                  <i class="glyphicon glyphicon-chevron-right"></i>
                                </a>
                              </span>
                            </td> -->
                            <?php foreach($warehouses as $key2 => $value2){ ?> 
                            <th><?php echo !isset($value->stock_product[$key2]) ? 0 : $value->stock_product[$key2]->z_stock_product_quantity; ?></th>
                            <?php } ?>
                            <!-- <td class="text-right">
                              <a href="<?=base_url('z_stock_product/update/z_stock_product_id/'.$value->z_stock_product_id)?>" data-toggle="tooltip" title="Update">
                                <i class="glyphicon glyphicon-edit"></i>
                              </a>
                            </td>
                            <td class="text-right">
                              <a onclick="check_delete('<?=$value->z_stock_product_id?>');" class="<?=check_permission('z_stock_product_delete', 'disable')?>" data-toggle="tooltip" title="Delete">
                                <i class="glyphicon glyphicon-remove"></i>
                              </a>
                            </td> -->

                          </tr>
                          <?php } ?>
                        </tbody>
                      </table>
                      <div class="page-area">
                        <span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
                        <?=$this->pagination->create_links()?>
                      </div>
                    </form>
                  </div>
                </div>
              
            </div>
          </div>


        </div>
      </div>

    </div>
    <?php } ?>












































    <?php $this->load->view('inc/footer-area.php'); ?>

  </body>
</html>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <i class="glyphicon glyphicon-remove"></i>
        </button>
        <h4 class="modal-title corpcolor-font">Detail</h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
<!-- myModal -->