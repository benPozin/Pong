import pygame
from settings import Settings
from paddle import Paddle
from ball import Ball

class PongGame:
    def __init__(self):
        pygame.init()
        self.screen = pygame.display.set_mode((Settings.WIDTH, Settings.HEIGHT))
        pygame.display.set_caption("Pong Game")

        self.clock = pygame.time.Clock()

        # Create paddles
        paddle_width, paddle_height = 10, 100
        self.left_paddle = Paddle(50, Settings.HEIGHT // 2 - paddle_height // 2, paddle_width, paddle_height)
        self.right_paddle = Paddle(Settings.WIDTH - 50 - paddle_width, Settings.HEIGHT // 2 - paddle_height // 2, paddle_width, paddle_height)

        # Create ball
        self.ball = Ball(Settings.WIDTH // 2, Settings.HEIGHT // 2, 10)

        # Scores
        self.left_score = 0
        self.right_score = 0
        self.font = pygame.font.Font(None, 36)

    def handle_events(self):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return False
        return True

    def handle_input(self):
        keys = pygame.key.get_pressed()
        if keys[pygame.K_w]:
            self.left_paddle.move(up=True)
        if keys[pygame.K_s]:
            self.left_paddle.move(up=False)
        if keys[pygame.K_UP]:
            self.right_paddle.move(up=True)
        if keys[pygame.K_DOWN]:
            self.right_paddle.move(up=False)

    def update(self):
        self.ball.move()
        if self.ball.rect.top <= 0 or self.ball.rect.bottom >= Settings.HEIGHT:
            self.ball.bounce_vertical()
        if self.ball.rect.colliderect(self.left_paddle.rect) or self.ball.rect.colliderect(self.right_paddle.rect):
            self.ball.bounce_horizontal()
        if self.ball.rect.left <= 0:
            self.right_score += 1
            self.ball.reset(Settings.WIDTH // 2, Settings.HEIGHT // 2)
        if self.ball.rect.right >= Settings.WIDTH:
            self.left_score += 1
            self.ball.reset(Settings.WIDTH // 2, Settings.HEIGHT // 2)

    def draw(self):
        self.screen.fill(Settings.BLACK)
        self.left_paddle.draw(self.screen)
        self.right_paddle.draw(self.screen)
        self.ball.draw(self.screen)
        pygame.draw.aaline(self.screen, Settings.WHITE, (Settings.WIDTH // 2, 0), (Settings.WIDTH // 2, Settings.HEIGHT))
        left_text = self.font.render(str(self.left_score), True, Settings.WHITE)
        right_text = self.font.render(str(self.right_score), True, Settings.WHITE)
        self.screen.blit(left_text, (Settings.WIDTH // 4, 20))
        self.screen.blit(right_text, (Settings.WIDTH * 3 // 4, 20))
        pygame.display.flip()

    def run(self):
        running = True
        while running:
            running = self.handle_events()
            self.handle_input()
            self.update()
            self.draw()
            self.clock.tick(Settings.FPS)

        pygame.quit()
