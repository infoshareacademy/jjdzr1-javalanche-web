
var $table = $('#table').bootstrapTable({
    data: data
});

$table.on('click', 'tbody > tr > td', function(e) {
    var table = $table.data('bootstrap.table'),
        $element = $(this),
        $tr = $element.parent(),
        row = table.data[$tr.data('index')],
        cellIndex = $element[0].cellIndex,
        $headerCell = table.$header.find('th:eq(' + cellIndex + ')'),
        field = $headerCell.data('field'),
        value = row[field];

    table.$el.trigger($.Event('click-cell.bs.table'), [value, row, $element]);
});

$table.on('click-cell.bs.table', function(e, value, row, $element) {
    alert("Value: " + value);
});