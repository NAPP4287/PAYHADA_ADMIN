import React, { Component, useEffect, useRef, createRef, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { postLogin } from '../apis'
import '../assets/css/login.css'

// images
import payhada_logo_gray from '../assets/images/payhada_logo_gray.png';

const Login = (props) => {
    const message = sessionStorage.getItem("message");
    const otpCode = sessionStorage.getItem("otpCode");

    const [loginId, setLoginId] = useState('');
    const [pwd, setPwd] = useState('');

    const login = () => {
        const _response = postLogin({
            id: loginId
            , pwd: pwd
        })

        // for test
        window.location.replace("/");
    }

    return (
        <div className="logincard">
            <div className="pmd-card card-default pmd-z-depth">
                <div className="login-card">
                    <div>
                        <div className="pmd-card-title card-header-border text-center">
                            <h1 className="loginlogo">
                                <img src={payhada_logo_gray} alt="Payhada Logo" width="265px" height="90px" />
                            </h1>
                            <h3>Sign In <span>with <strong>PAYHADA</strong></span></h3>
                        </div>
                        <div className="pmd-card-body">
                            <div className="alert alert-success" role="alert"> Oh snap! Change a few things up and try
                                submitting again.
                            </div>
                            {message === 'OTP' ?
                                <div>
                                    <div className="form-group pmd-textfield pmd-textfield-floating-label">
                                        <label htmlFor="inputError1" className="control-label pmd-input-group-label">
                                            Otp Code
                                        </label>
                                        <div className="input-group">
                                            <div className="input-group-addon">
                                                <i className="material-icons md-dark pmd-sm">lock_outline</i>
                                            </div>
                                            <input type="text" name="code" className="form-control" id="code" />
                                            <span className="pmd-textfield-focused"></span>
                                        </div>
                                    </div>
                                    <div>
                                        {otpCode ?
                                            <p className="login-fail text-center">[ {otpCode} ]</p>
                                            :
                                            null
                                        }
                                    </div>
                                </div>
                                :
                                <div>
                                    <div className="form-group pmd-textfield pmd-textfield-floating-label">
                                        <label htmlFor="inputError1"
                                               className="control-label pmd-input-group-label">Username</label>
                                        <div className="input-group">
                                            <div className="input-group-addon">
                                                <i></i>
                                            </div>
                                            <input
                                                type="text"
                                                className="form-control"
                                                value={loginId}
                                                onChange={e => setLoginId(e.target.value)} />
                                            <span className="pmd-textfield-focused"></span>
                                        </div>
                                    </div>
                                    <div className="form-group pmd-textfield pmd-textfield-floating-label">
                                        <label htmlFor="inputError1"
                                               className="control-label pmd-input-group-label">Password</label>
                                        <div className="input-group">
                                            <div className="input-group-addon">
                                                <i></i>
                                            </div>
                                            <input
                                                type="password"
                                                className="form-control"
                                                value={pwd}
                                                onChange={e => setPwd(e.target.value)} />
                                            <span className="pmd-textfield-focused"></span>
                                        </div>
                                    </div>
                                </div>
                            }
                        </div>
                        <div className="pmd-card-footer card-footer-no-border card-footer-p16 text-center">
                            <button onClick={login} type="button"
                                    className="btn pmd-ripple-effect btn-primary btn-block" id="loginBtn">Login
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login;