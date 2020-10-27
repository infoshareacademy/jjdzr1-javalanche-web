<%@ page import="java.time.LocalDate" %>
<div class="modal fade" id="modalWithdrawHolidayRequest" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Withdraw holiday request<span id="provider_mobile"></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form autocomplete="off" method="post" action="/withdrawHolidayRequest" id="withdrawHolidayRequestForm">

                <div class="modal-footer">
                    <input type="text" name="chosenDay" id="chosenDay" hidden>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="withdrawRequest" >Withdraw request</button>
                </div>
            </form>

        </div>
    </div>
</div>
<script>
    $('#modalWithdrawHolidayRequest').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-footer input').val(recipient)
    })
</script>