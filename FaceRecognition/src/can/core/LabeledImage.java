package can.core;

import java.io.Serializable;

public class LabeledImage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer label;
    private Double[][][] features;
    
    public LabeledImage(Integer label, Double[][][] features) {
        this.setLabel(label);
        this.setFeatures(features);
    }

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	public Double[][][] getFeatures() {
		return features;
	}

	public void setFeatures(Double[][][] features) {
		this.features = features;
	}
	
}
