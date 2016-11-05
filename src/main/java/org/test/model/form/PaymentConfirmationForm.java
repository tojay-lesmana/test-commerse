package org.test.model.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class PaymentConfirmationForm {

	@NotNull
	private String invoice;
	@NotNull
	private MultipartFile file;
	
	public PaymentConfirmationForm() {
		
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
