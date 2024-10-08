import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { dummyNavMenu } from "data/dummyData";
import {
  Collapse,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
} from "reactstrap";
import Logo from "assets/images/payhada_logo_1.png";
// components
import Header from "./Profile";
// interfaces
import { SubMenuType, NavType } from "interface/InterfaceNav";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";

const NavSide = () => {
  const navigate = useNavigate();
  const [menuDrop, setMenumDrop] = useState<ObjectBracketBooleanType>({
    0: false,
    1: false,
    2: false,
  });
  const [subActive, setSubActive] = useState<string>("");
  const [prevIdx, setPrevIdx] = useState<number>(0);
  const [fixIdx, setFixIdx] = useState<number | null>(null);
  const [collapseOpen, setCollapseOpen] = useState<boolean>(false);

  const onToggle = (idx: number) => {
    setMenumDrop({ ...menuDrop, [prevIdx]: false, [idx]: !menuDrop[idx] });
    setPrevIdx(idx);
  };

  const onClickMovePage = (url: string) => {
    navigate(url);
    setCollapseOpen(false);
  };

  const handleMenu = (name: string, url: string, mainIdx: number) => {
    onClickMovePage(url);
    setSubActive(name);
    setFixIdx(mainIdx);
  };

  const goMain = () => {
    onClickMovePage("/");
    setSubActive("");
    setMenumDrop({ 0: false, 1: false, 2: false });
    setFixIdx(null);
  };

  const dummyMenu = dummyNavMenu;

  const sortingMenu = (el: any) => {
    return el.sort((a: NavType | SubMenuType, b: NavType | SubMenuType) => {
      if (a.sort > b.sort) {
        return 1;
      }
      if (a.sort < b.sort) {
        return -1;
      }
      return 0;
    });
  };

  const handleCollapse = () => {
    setCollapseOpen(!collapseOpen);
  };

  return (
    <Navbar
      className="navbar-vertical fixed-left navbar-light bg-white"
      expand="md"
      id="sidenav-main">
      <div
        className="alignRow"
        style={{
          width: "100%",
          padding: "0 30px",
          alignItems: "center",
        }}>
        <button
          className="navbar-toggler paddingZero"
          type="button"
          onClick={handleCollapse}>
          <span className="navbar-toggler-icon" />
        </button>
        <NavbarBrand className="pt-0" onClick={goMain}>
          <img alt={"payhada logo"} className="navbar-brand-img" src={Logo} />
        </NavbarBrand>
      </div>
      <Collapse navbar isOpen={collapseOpen}>
        <div className="collapse-close">
          <button
            className="navbar-toggler"
            type="button"
            onClick={handleCollapse}>
            <span />
            <span />
          </button>
        </div>
        <Nav navbar>
          {sortingMenu(dummyMenu).map((el: NavType, index: number) => (
            <div key={index}>
              <NavItem className="cursor">
                <NavLink
                  className={`textBold ${
                    (menuDrop[index] || fixIdx === index) &&
                    "active menuOpenActive"
                  }`}
                  onClick={() => onToggle(index)}>
                  <i className={el.icon} />
                  {el.name}
                </NavLink>
              </NavItem>

              <Collapse isOpen={menuDrop[index] || fixIdx === index}>
                {sortingMenu(el.sub).map((el: SubMenuType, idx: number) => (
                  <li
                    key={idx}
                    value={el.name}
                    className={`menuSubItem ${
                      el.name === subActive && "fixMenu"
                    }`}
                    onClick={() => handleMenu(el.name, el.url, index)}>
                    {el.name}
                  </li>
                ))}
              </Collapse>
            </div>
          ))}
        </Nav>
      </Collapse>
      <Header />
    </Navbar>
  );
};

export default NavSide;
