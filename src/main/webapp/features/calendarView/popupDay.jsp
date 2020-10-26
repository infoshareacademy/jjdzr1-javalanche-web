<div class="modal fade" id="modalDay" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Place holiday request<span id="provider_mobile"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form autocomplete="off" method="post" action="/addHolidayRequest" id="addHolidayRequestForm">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">First day: </span>
                                </div>
                                <input id="StartDate" name="StartDate" type="date" placeholder="dd-mm-yyyy" class="start form-control form-field-width form-group"
                                       value="<%=LocalDate.now().toString()%>"
                                       min="<%=LocalDate.now().toString()%>" max="<%=LocalDate.now().plusDays(366).toString()%>">
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Last day: </span>
                                </div>
                                <input id="EndDate" name="EndDate" type="date" class="end col form-control form-field-width form-group"
                                       value="<%=LocalDate.now().toString()%>"
                                       min="<%=LocalDate.now().toString()%>" max="<%=LocalDate.now().plusDays(366).toString()%>">

                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addNewUserButton" >Place request</button>
                </div>
            </form>

        </div>
    </div>
</div>
<script>
    $('#StartDate').datepicker({
        minDate: 0,
        beforeShow: function() {
            $(this).datepicker('option', 'maxDate', $('#EndDate').val());
        }
    });
    $('#EndDate').datepicker({
        defaultDate: "+1w",
        beforeShow: function() {
            $(this).datepicker('option', 'minDate', $('#StartDate').val());
            if ($('#StartDate').val() === '') $(this).datepicker('option', 'minDate', 0);
        }
    });

</script>

<%--
<script>
    $('#modalDay').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('date') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })
</script>--%>
<%--
<script>
    $('#modalDay').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient2 = button.data('userId') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('New message to ' + recipient2)
        modal.find('.modal-body input').val(recipient2)
    })
</script>
--%>
