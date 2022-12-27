import React, { useState, useEffect, useRef } from 'react';
import { isMobile } from 'react-device-detect';
import { NavLink } from 'react-router-dom';

export class ErrorBoundary extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            hasError: false
        };
    }

    static getDerivedStateFromError(error) {
        // 다음 렌더링에서 폴백 UI가 보이도록 상태를 업데이트 합니다.
        // console.log('error', error);
        return { hasError: true };
    }

    componentDidCatch(error, errorInfo) {
        // 에러 리포팅 서비스에 에러를 기록할 수도 있습니다.
        // console.log('error', error);
        // console.log('error', errorInfo);
        // logErrorToMyService(error, errorInfo);
    }

    render() {
        if (this.state.hasError) {
            // 폴백 UI를 커스텀하여 렌더링할 수 있습니다.
            return (
                <div className="404page">
                    <div className="errorpage">
                        <div className="wrapper">
                            <div className="container">
                                <header className="header-primary">
                                    {/*<a href="index.html" rel="home"><img src="logo-icon.png" alt="logo" class="logo"></a> */}
                                </header>

                                <div className="content-primary">
                                    <h1 className="title"><span></span>
                                    </h1>
                                    <p className="description">페이지를 찾을 수 없습니다.</p>
                                    <div className="section-footer">
                                        <a href="/" className="btn btn-primary pmd-ripple-effect">메인으로</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }

        return this.props.children;
    }
}
