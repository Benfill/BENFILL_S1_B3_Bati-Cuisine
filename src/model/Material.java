package model;

public class Material extends Component {
	private double transportCost;
	private double qualityCoefficient;

	public double getTransportCost() {
		return transportCost;
	}

	public void setTransportCost(double transportCost) {
		this.transportCost = transportCost;
	}

	public double getQualityCoefficient() {
		return qualityCoefficient;
	}

	public void setQualityCoefficient(double qualityCoefficient) {
		this.qualityCoefficient = qualityCoefficient;
	}
}
