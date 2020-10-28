<%@ page import="com.infoshareacademy.DTO.UserDto" %>

<script type="text/javascript">
    $(window).on('load',function(){
        $('#emptyListModal').modal('show');
    });
</script>

<div class="modal fade" id="emptyListModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Attention</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p><%=user.getFirstName()%> <%=user.getLastName()%></p>
                <p>There are no teams in the system</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>