// var checkboxHandlerObj = {
//     init: function() {
//         $('#TagSubTags
//         input:checkbox[id^="tagIds"]').click(checkboxHandlerObj.parentClicked);
//         $('#TagSubTags
//         input:checkbox[id^="subTagIds"]').click(checkboxHandlerObj.childClicked)
//     },

//     parentClicked: function() {
//         var parentCheck = this.checked;
//         $('#TagSubTags input:checkbox[class="' + $(this).attr('id') +
//         '-subTag"]').each(function() {
//             this.checked = parentCheck
//         });
//     },

//     // childClicked: function() {
//     //     var temp = $(this).attr('class').split('-');
//     //     var parentId = temp[0];
//     //     $('#' + parentId)[0].checked = $('#TagSubTagss
//     input:checkbox[class="' + $(this).attr('class') + '"]:checked').length
//     !== 0;
//     // }
// };

$(document).ready(function() {
  $('#TagSubTags .tag-parent').change(function() {
    var parentId = $(this).attr('id');
    var parentCheck = this.checked;

    var subTagRow = " .subtagrow-" + parentId;
    var subTag = " ." + parentId + "-subTag";

    $('#TagSubTags' + subTag).each(function() { this.checked = parentCheck });

    if (parentCheck) {
      $('#TagSubTags' + subTagRow).slideDown("fast");
    } else {
      $('#TagSubTags' + subTagRow).slideUp("fast");
    }
  });
});
