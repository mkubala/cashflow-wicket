package pl.mkubala.cashflow.ui.component;

public aspect InitializableAspect pertarget(initializableConstruct()) {

    private boolean notInitializedYet = true;

    private pointcut initializableConstruct() : execution(Initializable+.new(..));

    after(final Initializable component) : initializableConstruct() && target(component) {
        if (notInitializedYet) {
            component.initialize();
            notInitializedYet = false;
        }
    }

}
