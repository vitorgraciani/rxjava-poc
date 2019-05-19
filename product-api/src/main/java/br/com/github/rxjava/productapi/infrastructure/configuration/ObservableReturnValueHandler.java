package br.com.github.rxjava.productapi.infrastructure.configuration;

import io.reactivex.Observable;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ObservableReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        Class<?> parameter = methodParameter.getParameterType();
        return parameter == Observable.class;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            modelAndViewContainer.setRequestHandled(true);
            return;
        }
        final DeferredResult<Object> deferredResult = new DeferredResult<>();
        Observable<?> observable = (Observable<?>) returnValue;

        observable.subscribe(result -> {
            deferredResult.setResult(result);
        }, errors -> {
            deferredResult.setErrorResult(errors);
        });
        WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult, modelAndViewContainer);


    }
}
