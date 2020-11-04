<div class="modal fade" id="addTeamModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Add new team:</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form autocomplete="off" method="post" action="/addteam" id="addTeamForm">
                <div class="modal-body">
                    <div class="row">
                        <div class="col">
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Name: </span>
                                </div>
                                <input type="text" name="addTeamName" class="form-control" id="inputTeamName"
                                       placeholder="Enter team's name" required>
                            </div>
                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend col-sm-4">
                                    <span class="input-group-text col-sm-12">Team leader: </span>
                                </div>
                                <select class="form-control" name="addTeamsLeader" id="inputTeamsLeader">
                                    <%for (UserDto user : teamLeaders) {%>
                                    <option value=<%=user.getId()%>><%=user.getFirstName() + " " + user.getLastName() + " " + user.getId()%>
                                    </option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="addNewUserButton"
                            <%if (teamLeaders.isEmpty()) {%>disabled<%}%>>Add
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row justify-content-sm-end" style="">
    <div class="col-md-2 .ml-md-auto">
        <button type="button" class="btn btn-primary btn-lg" aria-label="Basic example"
                data-toggle="modal" data-target="#addTeamModal"
                style="position: fixed;bottom: 20px;right: 20px; ">Add new team
        </button>
    </div>
</div>