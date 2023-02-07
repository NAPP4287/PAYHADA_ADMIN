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
// recoil
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";

const Header = () => {
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [dropDownOpen, setDropDownOpen] = useState<boolean>(false);

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
              <DropdownItem href="#pablo" onClick={(e) => e.preventDefault()}>
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
