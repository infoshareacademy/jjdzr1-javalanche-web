<div class="modal fade" id="modalPlaceHolidayRequest" tabindex="-1" role="dialog">
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
                                <input id="StartDate" name="StartDate" type="date"
                                       class="start form-control form-field-width form-group" disabled>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Last day: </span>
                                </div>
                                <input id="EndDate" name="EndDate" type="date"
                                       class="end col form-control form-field-width form-group"

                                       max="<%=LocalDate.now().plusDays(366).toString()%>">
                            </div>
                        </div>
                    </div>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary" id="placeRequestButton">Place request</button>
            </div>
            </form>

        </div>
    </div>
</div>

