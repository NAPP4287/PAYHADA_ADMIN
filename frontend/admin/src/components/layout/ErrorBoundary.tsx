import errorPerson from "assets/images/error_person.png";
import { useNavigate } from "react-router-dom";
import { Button } from "reactstrap";

const ErrorBoundary = () => {
  const navigate = useNavigate();
  return (
    <div className="alginCenter whiteBg textCenter">
      <div>
        <img
          src={errorPerson}
          className="error-person"
          alt="URI 잘못 들어왔을 때 나오는 이미지"
        />
        <h5>페이지를 찾을 수 없습니다.</h5>
        <br />
        <p>
          주소가 잘못 입력되었거나, 변경 혹은 삭제되어 요청하신 페이지를 찾을 수
          없습니다.
          <br />
          입력하신 주소가 정확한지 다시 한번 확인해 주세요.
        </p>
        <br />
        <br className="web" />
        <Button color="primary" onClick={() => navigate("/")}>
          홈으로 돌아가기
        </Button>
      </div>
    </div>
  );
};

export default ErrorBoundary;
