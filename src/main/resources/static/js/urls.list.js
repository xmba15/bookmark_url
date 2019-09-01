$(document).ready(function() {
  $('#TagSubTags input:checkbox[tag-id]').change(function() {
    var parentId = $(this).attr('tag-id');
    var parentCheck = this.checked;
    var subTagRow = " .subtagrow-" + parentId;

    // $('#TagSubTags input:checkbox[parent-tag-id="' + parentId + '"]')
    //     .each(function() {
    //       this.checked = parentCheck;
    //     });

    if (parentCheck) {
      $('#TagSubTags' + subTagRow).slideDown("fast");
    } else {
      $('#TagSubTags' + subTagRow).slideUp("fast");
    }
  });

  $('#TagSubTags input:checkbox[parent-tag-id]').click(function() {
    var parentId = $(this).attr('parent-tag-id');
    var subTagRow = " .subtagrow-" + parentId;
    var numChildChecked =
        $('#TagSubTags input:checkbox[parent-tag-id="' + parentId + '"]:checked')
            .length;
    if (numChildChecked == 0) {
      $('#TagSubTags input:checkbox[tag-id="' + parentId + '"]')[0].checked = false;
      $('#TagSubTags' + subTagRow).slideUp("fast");
    }
  });
});
