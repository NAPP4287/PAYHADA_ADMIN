import React, { Component, useEffect, useRef, createRef, useState } from 'react';
import { Link } from 'react-router-dom'
// import {getTest} from "../apis";

// const test = () => {
//     const _response = getTest()
// }

const Main = (props) => {
    console.log("Main props :::", props);

    return (
        <div>
            <Link to="/login">LOGIN</Link>
            {/*<button onClick={test} id="testBtn">TEST</button>*/}
        </div>
    )
}

export default Main;