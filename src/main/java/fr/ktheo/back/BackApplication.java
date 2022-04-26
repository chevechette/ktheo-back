package fr.ktheo.back;

import fr.ktheo.back.model.*;
import fr.ktheo.back.repository.AuctionStatusRepository;
import fr.ktheo.back.repository.CategoryRepository;
import fr.ktheo.back.repository.RoleRepository;
import fr.ktheo.back.repository.TransactionStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunnerRole(RoleRepository roleRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (roleRepository.count() != 0)
                    return;
                Role role1= new Role(RoleEnum.ROLE_CUSTOMER);
                Role role2 = new Role(RoleEnum.ROLE_ADMIN);
                Role role3 = new Role(RoleEnum.ROLE_ARTIST);
                Role role4 = new Role(RoleEnum.ROLE_GALERY);
                Role role5 = new Role(RoleEnum.ROLE_MODERATOR);
                List<Role> roleList = Arrays.asList(role1,role2,role3,role4,role5);
                roleRepository.saveAll(roleList);
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerCategory(CategoryRepository categoryRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (categoryRepository.count() != 0)
                    return;
                List<Category>  catList;

                Category painting = new Category(ECategory.CAT_PAINTING);
                Category nft = new Category(ECategory.CAT_VIRTUAL);
                Category statue = new Category(ECategory.CAT_SCULPTURE);
                catList = Arrays.asList(painting, nft, statue);
                categoryRepository.saveAll(catList);
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerAuctionStatus(AuctionStatusRepository auctionStatusRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (auctionStatusRepository.count() != 0)
                    return;
                List<AuctionStatus>  statusList;

                AuctionStatus open = new AuctionStatus(EAuctionStatus.AUCTION_OPEN);
                AuctionStatus closed = new AuctionStatus(EAuctionStatus.AUCTION_CLOSED);
                AuctionStatus pending = new AuctionStatus(EAuctionStatus.AUCTION_PENDING);
                AuctionStatus future = new AuctionStatus(EAuctionStatus.AUCTION_FUTURE);
                AuctionStatus cancelled = new AuctionStatus(EAuctionStatus.AUCTION_CANCELLED);
                statusList = Arrays.asList(open, closed, pending, future, cancelled);
                auctionStatusRepository.saveAll(statusList);
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerTransactionStatus(TransactionStatusRepository transactionStatusRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if (transactionStatusRepository.count() != 0)
                    return;
                List<TransactionStatus>  statusList;

                TransactionStatus cancelled = new TransactionStatus(ETransactionStatus.TRANSACTION_CANCELLED);
                TransactionStatus incomplete = new TransactionStatus(ETransactionStatus.TRANSACTION_INCOMPLETE);
                TransactionStatus pending = new TransactionStatus(ETransactionStatus.TRANSACTION_PENDING);
                TransactionStatus validated = new TransactionStatus(ETransactionStatus.TRANSACTION_VALIDATED);
                statusList = Arrays.asList(cancelled, incomplete, pending, validated);
                transactionStatusRepository.saveAll(statusList);
            }
        };
    }

}
