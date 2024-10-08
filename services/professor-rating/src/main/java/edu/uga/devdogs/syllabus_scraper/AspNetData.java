/**
 * Class stores data for hidden aspx values that are required in the body of the post request
 */
public class AspNetData {
    private String viewState;
    private String viewStateGenerator;
    private String eventValidation;

    /**
     * Constructor that
     * @param viewState hidden view state input
     * @param viewStateGenerator hidden state generator input
     * @param eventValidation hidden validation input
     */
    public AspNetData(String viewState, String viewStateGenerator, String eventValidation) {
        this.viewState = viewState;
        this.viewStateGenerator = viewStateGenerator;
        this.eventValidation = eventValidation;
    }

    /**
     * @return viewstate
     */
    public String getViewState() {
        return viewState;
    }

    /**
     * Set view state
     * @param viewState the view state
     */
    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    /**
     * @return view state generator
     */
    public String getViewStateGenerator() {
        return viewStateGenerator;
    }

    /**
     * Set view state generator
     * @param viewStateGenerator the view state generator
     */
    public void setViewStateGenerator(String viewStateGenerator) {
        this.viewStateGenerator = viewStateGenerator;
    }

    /**
     * @return event validation
     */
    public String getEventValidation() {
        return eventValidation;
    }

    /**
     * Set event validation
     * @param eventValidation the event validation
     */
    public void setEventValidation(String eventValidation) {
        this.eventValidation = eventValidation;
    }
}
