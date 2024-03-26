package services;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        // Cette partie est automatiquement gérée par l'annotation @InjectMocks
    }

    @Test
  void retrieveAllInvoices() {
        List<Invoice> expectedInvoices = Arrays.asList(new Invoice(), new Invoice());
        when (invoiceRepository.findAll()).thenReturn(expectedInvoices);

        List<Invoice> result = invoiceService.retrieveAllInvoices();

        assertEquals(expectedInvoices, result);
        verify(invoiceRepository).findAll();
    }
    @Test
    void cancelInvoice_ShouldSetInvoiceAsArchived() {
        Invoice invoice = new Invoice();
        invoice.setArchived(false);
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        invoiceService.cancelInvoice(1L);


        verify(invoiceRepository).save(invoice);
    }
    @Test
    void retrieveInvoice_ShouldReturnInvoiceWhenFound() {
        Invoice expectedInvoice = new Invoice();
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(expectedInvoice));

        Invoice result = invoiceService.retrieveInvoice(1L);

        assertEquals(expectedInvoice, result);
        verify(invoiceRepository).findById(1L);
    }

}
