import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { dummyNavMenu } from "dummy/dummyData";
import {
  Collapse,
  Navbar,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
} from "reactstrap";
import Logo from "assets/images/payhada_logo_1.png";

const NavSide = () => {
  const navigate = useNavigate();
  const [menuDrop, setMenumDrop] = useState<any>({
    0: false,
    1: false,
    2: false,
    3: false,
    4: false,
    5: false,
  });
  const [prevIdx, setPrevIdx] = useState(0);

  const onToggle = (idx: number) => {
    setMenumDrop({ ...menuDrop, [prevIdx]: false, [idx]: !menuDrop[idx] });
    setPrevIdx(idx);
  };

  const onClickMovePage = (url: string) => {
    navigate(url);
  };

  const dummyMenu = dummyNavMenu;

  return (
    <Navbar
      className="navbar-vertical fixed-left navbar-light bg-white"
      expand="md"
      id="sidenav-main">
      <NavbarBrand className="pt-0">
        <img alt={"payhada logo"} className="navbar-brand-img" src={Logo} />
      </NavbarBrand>
      <Collapse navbar>
        <Nav navbar>
          {dummyMenu.map((el, idx) => (
            <>
              <NavItem key={idx}>
                <NavLink
                  className={`textBold ${
                    menuDrop[idx] && "active menuOpenActive"
                  }`}
                  onClick={() => onToggle(idx)}>
                  <i className={el.lmenu.lmenuIcon} />
                  {el.lmenu.lmenuNm}
                </NavLink>
              </NavItem>

              <Collapse isOpen={menuDrop[idx]}>
                {el.smenu.map((el, idx) => (
                  <li
                    key={idx}
                    className="menuSubItem"
                    onClick={() => onClickMovePage(el.url)}>
                    {el.smenuNm}
                  </li>
                ))}
              </Collapse>
            </>
          ))}
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default NavSide;
