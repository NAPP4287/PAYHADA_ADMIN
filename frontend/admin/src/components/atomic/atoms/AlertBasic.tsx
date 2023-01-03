import { useRecoilState } from "recoil";
import { commonAlertState } from "recoil/stateAlert";
import { BasicAlertType } from "interface/InterfaceCommonAlert";
import { Modal, Button } from "reactstrap";

function BasicAlert() {
  const [commonAlertInfo, setCommonAlertInfo] =
    useRecoilState<BasicAlertType>(commonAlertState);

  const closeModal = () => {
    setCommonAlertInfo({ ...commonAlertInfo, isOpen: false });
  };

  const actionButton = () => {
    if (commonAlertInfo.action !== undefined) {
      commonAlertInfo.action();
    }
    closeModal();
  };

  const cancelButton = () => {
    if (commonAlertInfo.cancel !== undefined) {
      commonAlertInfo.cancel();
    }
    closeModal();
  };

  return (
    <Modal className="modal-dialog-centered" isOpen={commonAlertInfo.isOpen}>
      <div className="modalWrap">
        <div className="modalHeader">
          <h4 className="modal-title" id="modal-title-default">
            {commonAlertInfo.title}
          </h4>
          <button
            aria-label="Close"
            className="close"
            data-dismiss="modal"
            type="button"
            onClick={closeModal}>
            <span aria-hidden={true}>×</span>
          </button>
        </div>
        <div className="modalBody">
          <p>{commonAlertInfo.content}</p>
        </div>
        <div className="modalBottom">
          <Button color="primary" type="button" onClick={actionButton}>
            {commonAlertInfo.buttonText.confirm}
          </Button>
          {commonAlertInfo.AlertType !== "normal" && (
            <Button
              color="secondary"
              outline
              type="button"
              onClick={cancelButton}>
              {commonAlertInfo.buttonText.cancel}
            </Button>
          )}
        </div>
      </div>
    </Modal>
  );
}

export default BasicAlert;
