$(document).ready(function() {
  $('#TagSubTags .tag-parent').change(function() {
    var parentId = $(this).attr('id');
    var parentCheck = this.checked;
    var subTagRow = " .subtagrow-" + parentId;
    var parentTag = parentId;

    $('#TagSubTags input:checkbox[parent-tag="' + parentTag + '"]')
        .each(function() {
          this.checked = parentCheck;
        });

    if (parentCheck) {
      $('#TagSubTags' + subTagRow).slideDown("fast");
    } else {
      $('#TagSubTags' + subTagRow).slideUp("fast");
    }
  });

  $('#TagSubTags input:checkbox[parent-tag^="tagIds"]').click(function() {
    var parentId = $(this).attr('parent-tag');
    var subTagRow = " .subtagrow-" + parentId;
    var numChildChecked =
        $('#TagSubTags input:checkbox[parent-tag="' + parentId + '"]:checked')
            .length;
    if (numChildChecked == 0) {
      $('#TagSubTags #' + parentId)[0].checked = false;
      $('#TagSubTags' + subTagRow).slideUp("fast");
    }
  });
});
