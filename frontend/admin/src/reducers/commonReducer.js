//액션 타입
const SET_LANGUAGE = 'common/SET_LANGUAGE';

//액션 생성 함수
export const setLanguage = (data) => ({
    type: SET_LANGUAGE,
    lang: data,
});

//초기값
const initialState = {
    lang: '',
};

//리듀서
const commonReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_LANGUAGE:
            return {
                ...state,
                lang: action.lang,
            };
        default:
            return state;
    }
};

export default commonReducer;