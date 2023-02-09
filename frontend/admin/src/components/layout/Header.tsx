import { useState } from "react";
import {
  Nav,
  DropdownToggle,
  Media,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
} from "reactstrap";
// css
import styles from "assets/css/Header.module.css";
// apis
import { changeLanguage } from "apis/settingApis";
import { callLogout } from "apis/loginApis";
// recoil
import { useRecoilState, useResetRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";
import { commonAlertState } from "recoil/stateAlert";

const Header = () => {
  const [dropDownOpen, setDropDownOpen] = useState<boolean>(false);
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [commonAlertInfo, setCommonAlertInfo] =
    useRecoilState(commonAlertState);
  const resetUserInfo = useResetRecoilState(userInfoState);

  const onToggle = () => {
    setDropDownOpen(!dropDownOpen);
  };

  const reqChangeLang = async (lang: string) => {
    const result = await changeLanguage({
      languageCd: lang,
      userNo: userInfo.userNo,
    });

    if (result.resultCode === "S0000") {
      setUserInfo({ ...userInfo, languageCd: lang });
    }
  };

  const reqLogout = async () => {
    const result = await callLogout();

    if ((result.resultCode = "E2011")) {
      resetUserInfo();
      localStorage.setItem("session", "logout");
    }
  };

  const handleLogoutModal = () => {
    setCommonAlertInfo({
      ...commonAlertInfo,
      isOpen: true,
      title: "Warning",
      alertType: "error",
      content: "로그아웃 하시겠습니까?",
      action: reqLogout,
    });
  };

  return (
    <>
      <div className={styles.containerWrap}>
        <Nav navbar>
          <UncontrolledDropdown isOpen={dropDownOpen} onClick={onToggle}>
            <DropdownToggle nav>
              <Media className="align-items-center">
                <span className="avatar avatar-sm rounded-circle" />
                <Media className="ml-2 d-none d-lg-block">
                  <span className="mb-0 text-sm font-weight-bold secondary">
                    {userInfo.loginId}
                  </span>
                </Media>
              </Media>
            </DropdownToggle>
            <DropdownMenu className="dropdown-menu-arrow">
              <DropdownItem to="/admin/user-profile">
                <i className="ni ni-single-02" />
                <span>My profile</span>
              </DropdownItem>
              <DropdownItem
                to="/admin/user-profile"
                onClick={() => reqChangeLang("ko")}>
                <i className="ni ni-settings-gear-65" />
                <span>한국어</span>
              </DropdownItem>
              <DropdownItem
                to="/admin/user-profile"
                onClick={() => reqChangeLang("en")}>
                <i className="ni ni-settings-gear-65" />
                <span>English</span>
              </DropdownItem>
              <DropdownItem divider />
              <DropdownItem href="#pablo" onClick={handleLogoutModal}>
                <i className="ni ni-user-run" />
                <span>Logout</span>
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>
      </div>
    </>
  );
};

export default Header;
