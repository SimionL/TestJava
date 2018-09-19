public class ManageSale {

	Sale sale;
	int occurrencesNumber;
	AdjustmentOperation adOp;
	private Long id;

	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public int getOccurrencesNumber() {
		return occurrencesNumber;
	}
	public void setOccurrencesNumber(int occurrencesNumber) {
		this.occurrencesNumber = occurrencesNumber;
	}
	public AdjustmentOperation getAdOp() {
		return adOp;
	}
	public void setAdOp(AdjustmentOperation adOp) {
		this.adOp = adOp;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int hashCode() {

		int result = 31 * (int) (id ^ (id >>> 32));
		if(sale != null) {
			result = 31 * result + (int) ( new Double(sale.getValue()).intValue() ^ (new Double(sale.getValue()).intValue() >>> 32));
			result = 31 * result + sale.getProductType().hashCode();
		}
		return result;
	}
}