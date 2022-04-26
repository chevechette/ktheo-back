package fr.ktheo.back.model;

public enum EAuctionStatus {
    AUCTION_OPEN,
    AUCTION_CLOSED,
    AUCTION_PENDING, // i.e. no new bid accepted until resumed
    AUCTION_FUTURE,
    AUCTION_CANCELLED
}
